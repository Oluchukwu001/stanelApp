package stanel.app.store.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import stanel.app.store.profile.dto.ProfileRequestDTO;
import stanel.app.store.profile.dto.ProfileResponseDTO;
import stanel.app.store.profile.services.CreateUser;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public ProfileResponseDTO createUser(@RequestBody ProfileRequestDTO requestDTO) {
        return ProfileResponseDTO.mapToDTO(createUser.execute(requestDTO));
    }

}
