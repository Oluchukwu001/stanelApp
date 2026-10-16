package stanel.app.store.profile.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.NotFoundException;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.store.Store;
import stanel.app.store.util.ServiceUtil;

@RequiredArgsConstructor
@Service 
public class GetUserStores {
    private final ProfileRepository profileRespository;

    public List<Store> getUserStore(Long id) {
        Profile userProfile = fetchUserProfile(id);
        List<Store> stores = userProfile.getStores();
        if (stores.isEmpty()) {
            throw new NotFoundException("No store found for this user");
        }
        return stores;
    }

    private Profile fetchUserProfile(Long id) {
        return ServiceUtil.findOrThrow(profileRespository, id, "User profile not found");
    }
}
