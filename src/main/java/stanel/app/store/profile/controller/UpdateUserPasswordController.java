package stanel.app.store.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import stanel.app.store.profile.dto.UpdatePasswordRequestDTO;
import stanel.app.store.profile.services.UpdateUserPassword;

@RestController
@RequestMapping("/api/v1/profiles")
public class UpdateUserPasswordController {

    private final UpdateUserPassword updateUserPassword;

    public UpdateUserPasswordController(UpdateUserPassword updateUserPassword) {
        this.updateUserPassword = updateUserPassword;
    }

    @PutMapping("/{profileId}/password")
    @ResponseStatus(HttpStatus.OK)
    public String updatePassword(@PathVariable Long profileId,
            @Valid @RequestBody UpdatePasswordRequestDTO requestDTO) {

        return updateUserPassword.updateUserPassword(profileId, requestDTO);
    }
}
