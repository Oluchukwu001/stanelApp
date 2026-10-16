package stanel.app.store.profile.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.NotFoundException;
import stanel.app.store.profile.ProfileRepository;

@Service
@RequiredArgsConstructor
public class DeleteUserProfile {
    private final ProfileRepository profileRepository;

    public String deleteUserProfile(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new NotFoundException("User profile not found");
        }
        profileRepository.deleteById(profileId);
        return "User profile deleted successfully";
    }

}
