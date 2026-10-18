package stanel.app.store.resetPassword;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;

import org.springframework.stereotype.Component;
@Component 
public class TokenUtil {

    private final SecureRandom secureRandom = new SecureRandom();

    public String generateToken() {
        byte[] byteRandom = new byte[32];
        secureRandom.nextBytes(byteRandom);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(byteRandom);
    }

    public String hashToken(String rawToken) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

    }
}
