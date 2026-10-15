package stanel.app.store.profile.services;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRespository;
import stanel.app.store.user.enums.Role;


@RequiredArgsConstructor 
@Data 
public class GetStoreOwners {
    private final ProfileRespository profileRespository;
    public List<Profile> execute(){
        return profileRespository.findAllByRole(Role.STOREOWNER);
    } 
}
