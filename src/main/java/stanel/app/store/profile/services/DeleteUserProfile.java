package stanel.app.store.profile.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.NotFoundException;
import stanel.app.store.profile.ProfileRespository;

@Service
@RequiredArgsConstructor
public class DeleteUserProfile {
    private final ProfileRespository profileRespository;

    public String deleteUserProfile(Long profileId) {
        if (!profileRespository.existsById(profileId)) {
            throw new NotFoundException("User profile not found");
        }
        profileRespository.deleteById(profileId);
        return "User profile deleted successfully";
    }

}
