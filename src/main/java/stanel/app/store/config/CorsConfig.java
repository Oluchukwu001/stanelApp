
// package stanel.app.store.config;

// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// public class CorsConfig {

//         @Bean
//         public CorsConfigurationSource corsConfigurationSource() {

//                 CorsConfiguration config = new CorsConfiguration();

//                 // Frontend development URLs
//                 config.setAllowedOrigins(List.of(
//                                 "http://localhost:5173",
//                                 "http://127.0.0.1:5173",
//                                 "http://localhost:3000",
//                                 "http://127.0.0.1:3000"));

//                 // HTTP methods the frontend can use
//                 config.setAllowedMethods(List.of(
//                                 "GET",
//                                 "POST",
//                                 "PUT",
//                                 "PATCH",
//                                 "DELETE",
//                                 "OPTIONS"));

//                 // Request headers permitted by the backend
//                 config.setAllowedHeaders(List.of(
//                                 "Authorization",
//                                 "Content-Type",
//                                 "Accept",
//                                 "Origin"));

//                 // Allow the frontend to read the JWT response header if used
//                 config.setExposedHeaders(List.of("Authorization"));

//                 // Supports cookies and credentialed requests
//                 config.setAllowCredentials(true);

//                 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

//                 // Apply CORS to all API routes
//                 source.registerCorsConfiguration("/**", config);

//                 return source;
//         }
// }
