package stanel.app.store.profile.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRespository;
import stanel.app.store.profile.dto.UpdatePasswordRequestDTO;
import stanel.app.store.user.User;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service 
@RequiredArgsConstructor
public class UpdateUserPassword {
    private final PasswordEncoder passwordEncoder;
    private final ProfileRespository profileRespository;

    public String updateUserPassword(Long profileId, UpdatePasswordRequestDTO requestDTO) {
        validatePasswordUpdateCredentials(profileId, requestDTO.newPassword());
        Profile profile = fetchUserProfile(profileId);
        User user = profile.getUser();
        if (!passwordEncoder.matches(requestDTO.oldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(requestDTO.newPassword()));
        profileRespository.save(profile);
        return "Password successfully updated";

    }

    private Profile fetchUserProfile(Long profileID){
        return  ServiceUtil.findOrThrow(profileRespository, profileID, "User Profile");
    }

    private void validatePasswordUpdateCredentials(Long profileId, String newPassword) {
        ValidationUtil.requireMinLength(newPassword, 8, "Password must be at least 8 characters");
        ValidationUtil.requirePositive(profileId, "Profile ID must be postive");
    }

}

