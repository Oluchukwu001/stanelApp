package stanel.app.store.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.services.DeleteUserProfile;
import stanel.app.store.user.dtos.Response;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class DeleteUserProfileController {

    private final DeleteUserProfile deleteUserProfile;

    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProfile(@PathVariable Long profileId) {
        return deleteUserProfile.deleteUserProfile(profileId);
    }
}
