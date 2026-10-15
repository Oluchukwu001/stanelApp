package stanel.app.store.profile.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.dto.ProfileResponseDTO;
import stanel.app.store.profile.services.GetStaffs;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class GetStaffsController {

    private final GetStaffs getStaffs;

    @GetMapping("/staff")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileResponseDTO> getStaffs() {

        return ProfileResponseDTO.mapToDtos(getStaffs.execute());
    }
}
