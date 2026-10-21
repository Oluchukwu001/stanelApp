package stanel.app.store.profile.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;


import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.profile.dto.ProfileRequestDTO;
import stanel.app.store.store.Store;
import stanel.app.store.store.StoreRepository;
import stanel.app.store.user.User;
import stanel.app.store.util.ServiceUtil;

@Service
public class UpdateProfile {
    private final ProfileRepository profileRespository;
    private final StoreRepository storeRepository;

    public UpdateProfile(ProfileRepository profileRespository, StoreRepository storeRepository) {
        this.profileRespository = profileRespository;
        this.storeRepository = storeRepository;
    }

    public Profile updateProfile(Long id, ProfileRequestDTO requestDTO) {
        Profile user = fetchProfileUser(id);
        setProfileAttributeIfExists(user, requestDTO);
        return profileRespository.save(user);
    }

    private void setProfileAttributeIfExists(Profile profile, ProfileRequestDTO requestDTO) {
        User user = profile.getUser();
        ServiceUtil.setIfPresent(requestDTO.gender(), profile::setGender);
        ServiceUtil.setIfPresent(requestDTO.email(), user::setEmail);
        ServiceUtil.setIfPresent(requestDTO.firstName(), profile::setFirstName);
        ServiceUtil.setIfPresent(requestDTO.lastName(), profile::setLastName);
        ServiceUtil.setIfPresent(requestDTO.phoneNumber(), profile::setPhoneNumber);
        ServiceUtil.setIfPresent(requestDTO.role(), user::setRole);
        profile.setStores(fetchStore(requestDTO.storeIds()));

    }

    private List<Store> fetchStore(List<Long> storeIds) {
        List<Store> stores = new ArrayList<>();
        if (storeIds != null && !storeIds.isEmpty()) {
            stores = storeIds.stream().filter(Objects::nonNull).map(el -> {
                return ServiceUtil.findOrThrow(storeRepository, el, "Store");
            }).toList();
        }
        return stores;
    }

    private Profile fetchProfileUser(Long id) {
        return ServiceUtil.findOrThrow(profileRespository, id, "profile user not found");
    }

}
