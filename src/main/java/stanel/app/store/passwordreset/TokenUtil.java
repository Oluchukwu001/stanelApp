package stanel.app.store.passwordreset;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;

public class TokenUtil {
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateToken(){
        byte[] hashBytes = new byte[32];
        secureRandom.nextBytes(hashBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
    }

    public String hashToken(String rawToken){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashToken = messageDigest.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashToken);
        } catch (NoSuchAlgorithmException e) {
           throw new IllegalStateException(e);
        }
    }
}
