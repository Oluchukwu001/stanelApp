package stanel.app.store.secure;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * FIX #3 — authorization rules.
 *
 * 1. `hasRole("'ADMIN'")` demanded the authority ROLE_'ADMIN'. hasRole() adds
 * the ROLE_ prefix itself, so the literal quotes made it unmatchable.
 * UserDetail grants "ROLE_" + role, i.e. ROLE_ADMIN → use hasRole("ADMIN").
 *
 * 2. PATCH was never listed, so /orders/{id}/status and /cart/** fell through
 * to anyRequest().authenticated() with no role check.
 *
 * 3. `/api/v1/user-login` is now matched by method AND listed before the
 * blanket /api/v1/** rules, with OPTIONS + /error permitted too.
 *
 * 4. A real entry point is set, so an unauthenticated call answers 401 (with
 * www-authenticate) instead of the default 403 "Access Denied" — much
 * easier to debug from the browser.
 *
 * 5. CORS is wired for the Vite dev server, so you can drop the proxy if you
 * prefer calling http://localhost:8080 directly.
 */
@Configuration
public class SecurityConfiguration {

    /** Override in application.yaml: app.cors.allowed-origins */
    @Value("${app.cors.allowed-origins:http://localhost:5173,http://127.0.0.1:5173}")
    private List<String> allowedOrigins;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailService userDetailsService) {
        return new JwtAuthFilter(jwtService, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeHttpRequests(auth -> auth
                        // ── pre-flight + error dispatch ──────────────────────────────
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/error").permitAll()

                        // ── public auth endpoints (must precede /api/v1/** rules) ────
                        .requestMatchers(HttpMethod.POST, "/api/v1/user-login").permitAll()
                        .requestMatchers("/api/v1/password-reset/**").permitAll()

                        // ── OPTIONAL: public storefront browsing. Uncomment to let
                        // anonymous visitors read the catalogue.
                        // .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                        // .requestMatchers(HttpMethod.GET, "/api/v1/stores/**").permitAll()

                        // ── role rules (no inner quotes!) ────────────────────────────
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "MANAGER", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyRole("ADMIN", "MANAGER", "SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("ADMIN", "SUPERADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept", "X-Requested-With"));
        config.setExposedHeaders(List.of("Location"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
