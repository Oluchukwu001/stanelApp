package stanel.app.store.buyersauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
   
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFillter jwtAuthFillter) {
        httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/v1/store-owner").permitAll().anyRequest().authenticated())
                .addFilterAt(jwtAuthFillter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
