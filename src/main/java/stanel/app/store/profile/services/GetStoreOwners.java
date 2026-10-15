package stanel.app.store.profile.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRespository;
import stanel.app.store.user.enums.Role;

@Service 
@RequiredArgsConstructor 
public class GetStoreOwners {
    private final ProfileRespository profileRespository;
    public List<Profile> execute(){
        return profileRespository.findAllByRole(Role.STOREOWNER);
    } 
}
