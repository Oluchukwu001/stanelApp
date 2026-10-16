package stanel.app.store.profile.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.user.enums.Role;



@RequiredArgsConstructor 
@Service 
public class GetStaffs {
    private final ProfileRepository profileRepository;
    public List<Profile> execute(){
        return profileRepository.findAllByRoleIn(List.of(Role.ADMIN, Role.CASHIER, Role.CLEANER, Role.DRIVER, Role.MANAGER, Role.SALESBOY, Role.SALESGIRL, Role.SUPERADMIN));
    }     
}
