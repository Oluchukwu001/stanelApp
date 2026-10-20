package stanel.app.store.profile.services;

import org.springframework.stereotype.Service;

import stanel.app.store.exception.NotFoundException;
import stanel.app.store.profile.ProfileRepository;

@Service
public class DeleteUserProfile {
    private final ProfileRepository profileRepository;

    public DeleteUserProfile(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public String deleteUserProfile(Long profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new NotFoundException("User profile not found");
        }
        profileRepository.deleteById(profileId);
        return "User profile deleted successfully";
    }

}
