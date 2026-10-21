package stanel.app.store.profile.services;

import java.util.List;

import org.springframework.stereotype.Service;


import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.user.enums.Role;

@Service
public class GetStoreOwners {
    private final ProfileRepository profileRepository;

    public GetStoreOwners(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> execute() {
        return profileRepository.findAllByRole(Role.STOREOWNER);
    }
}
