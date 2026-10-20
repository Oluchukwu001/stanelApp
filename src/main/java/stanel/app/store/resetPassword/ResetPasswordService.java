package stanel.app.store.resetPassword;


import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import stanel.app.store.exception.BadRequestException;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.util.ValidationUtil;

@Service
public class ResetPasswordService {
    private final ProfileRepository profileRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final TokenUtil tokenUtil;
    private final SendEmail sendEmail;

    private static final  Duration TOKEN_TTL= Duration.ofMinutes(15);

    private final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    public ResetPasswordService(ProfileRepository profileRepository, TokenUtil tokenUtil,PasswordResetRepository passwordResetRepository, SendEmail sendEmail) {
        this.profileRepository = profileRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.tokenUtil = tokenUtil;
        this.sendEmail = sendEmail;
       
    }

    @Transactional
    public void requestReset(RequestResetDTO requestDTO) {
        validateEmail(requestDTO.email());
        Profile user = fetchProfile(requestDTO.email());
        if (user == null) {
            return;
        }

        passwordResetRepository.deleteByProfile(user);
        String token = tokenUtil.generateToken();

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setTokenHash(tokenUtil.hashToken(token));
        passwordResetToken.setProfile(user);
        passwordResetToken.setUsed(false);
        passwordResetToken.setExpiration(Instant.now().plus(TOKEN_TTL));
        passwordResetRepository.save(passwordResetToken);
        try {
            sendEmail.sendPasswordResetEmail(user.getUser().getEmail(),token);
        } catch (Exception e) {
           logger.error("Failed to send password reset email",e);
        }

    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new BadRequestException("Email is required");
        }
        ValidationUtil.requireValidEmail(email, "Enter a valid email");
    }

    private Profile fetchProfile(String email) {
        return profileRepository.findByUser_EmailIgnoreCase(email).orElse(null);
    }
}
