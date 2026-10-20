package stanel.app.store.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import stanel.app.store.profile.services.DeleteUserProfile;


@RestController
@RequestMapping("/api/v1/profiles")
public class DeleteUserProfileController {

    private final DeleteUserProfile deleteUserProfile;
    

    public DeleteUserProfileController(DeleteUserProfile deleteUserProfile) {
        this.deleteUserProfile = deleteUserProfile;
    }


    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProfile(@PathVariable Long profileId) {
        return deleteUserProfile.deleteUserProfile(profileId);
    }
}
