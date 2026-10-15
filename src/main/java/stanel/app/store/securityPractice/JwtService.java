// package stanel.app.store.securityPractice;

// import java.util.Date;

// import javax.crypto.SecretKey;

// import org.springframework.beans.factory.annotation.Value;

// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import jakarta.annotation.PostConstruct;
// import stanel.app.store.staff.Staff;

// @Service
// public class JwtService {
//     @Value("${app.jwt.secret}")
//     private String secret;
//     @Value("{app.jwt.expiration-ms}")
//     private Long expirationMs;

//     SecretKey secretKey;

//     @PostConstruct
//     void init() {
//         byte[] keyByte = Decoders.BASE64.decode(secret);
//         secretKey = Keys.hmacShaKeyFor(keyByte);
//     }

//     public String generateToken(Staff staff) {
//         return Jwts.builder()
//                 .subject(staff.getEmail())
//                 .claim("role", staff.getRole())
//                 .issuedAt(new Date())
//                 .expiration(new Date(System.currentTimeMillis() + expirationMs))
//                 .compact();
//     }

// }
