package stanel.app.store.resetPassword;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.BadRequestException;
import stanel.app.store.exception.NotFoundException;
import stanel.app.store.passwordreset.ResetPasswordRequestDTO;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.util.ValidationUtil;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final ProfileRepository profileRepository;
    private final TokenUtil tokenUtil;

    public void resetPassword(RequestResetDTO requestDTO) {
        validateEmail(requestDTO.email());
        Profile user = fetchProfile(requestDTO.email());
        if(user==null){
            return ;
        }

        // List<PasswordResetToken> oldTokens = user.
        String token = tokenUtil.generateToken();


    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new BadRequestException("Email is required");
        }
        ValidationUtil.requireValidEmail(email, "Enter a validate email");
    }

    private Profile fetchProfile(String email) {
        return profileRepository.findByUser_EmailIgnoreCase(email).orElse(null);
    }
}
