package stanel.app.store.passwordreset;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;

@Service 
@RequiredArgsConstructor 
public class PasswordResetService {
    private final ProfileRepository profileRepository;
    private final PasswordRestRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendPasswordRestLinkService sendPasswordRestLinkService;

    private final SecureRandom secureRandom = new SecureRandom();
    @Value ("${app.password-reset-url}")
    private final String passwordResetUrl;
    @Transactional 
    public void forgotPassword(String email){
        Profile profile = profileRepository.findByUser_EmailIgnoreCase(email).orElse(null);
        if(profile==null){
            return ;
        }

        List<PasswordReset> oldToken = passwordResetRepository.findAllByProfileAndUsedFalse(profile);
        oldToken.forEach(el->el.setUsed(true));

        byte[] randomByte = new byte[32];
        secureRandom.nextBytes(randomByte);

        String rawToken = Base64.getUrlEncoder().withoutPadding().encodeToString(randomByte);

        PasswordReset rest = new PasswordReset();
        rest.setToken(rawToken);
        rest.setProfile(profile);
        rest.setExpiresAt(Instant.now().plusSeconds(900));
        rest.setUsed(false);
        
        passwordResetRepository.save(rest);

        String ResetLink = passwordResetUrl + "?Token="+ rawToken;

        sendPasswordRestLinkService.sendPasswordRestLink(profile.getUser().getEmail(), ResetLink);
    }
    
}
