package stanel.app.store.profile.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import stanel.app.store.profile.Profile;
import stanel.app.store.profile.dto.ProfileRequestDTO;
import stanel.app.store.profile.dto.ProfileResponseDTO;
import stanel.app.store.profile.services.UpdateProfile;
import stanel.app.store.user.dtos.Response;

@RestController
@RequestMapping("/api/v1/profiles")
public class UpdateProfileController {

    private final UpdateProfile updateProfile;
    

    public UpdateProfileController(UpdateProfile updateProfile) {
        this.updateProfile = updateProfile;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateProfile(
            @PathVariable Long id,
            @RequestBody ProfileRequestDTO requestDTO) {

        Profile profile = updateProfile.updateProfile(id, requestDTO);

        return new Response(ProfileResponseDTO.mapToDTO(profile), "Profile updated successfully");
    }
}

