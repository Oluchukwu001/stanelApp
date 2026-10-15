package stanel.app.store.buyersauth;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import stanel.app.store.buyers.StoreOwner;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secret;
    @Value("${app.jwt.expiration-ms}")
    private Long expirationMs;

    SecretKey secretKey;

    @PostConstruct
    void init() {
        byte[] byteKey = Decoders.BASE64.decode(secret);
        secretKey = Keys.hmacShaKeyFor(byteKey);
    }

    public String generateToken(StoreOwner storeOwner) {
        return Jwts.builder()
                .subject(storeOwner.getEmail())
                .claim("ROLE", storeOwner.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();

    }

    public String extractOwnerEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String email = extractOwnerEmail(token);
            String dataBaseEmail = userDetails.getUsername();
            return email.equals(dataBaseEmail);
        } catch (JwtException e) {
            return false;
        }
    }
}
