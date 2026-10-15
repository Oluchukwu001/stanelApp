package stanel.app.store.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import stanel.app.store.profile.Profile;
import stanel.app.store.user.enums.Role;

@Data
@AllArgsConstructor
public class ProfileResponseDTO {
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;

    public static ProfileResponseDTO mapToDTO(Profile profile) {
        return new ProfileResponseDTO(profile.getUser().getEmail(), profile.getPhoneNumber(), profile.getFirstName(),
                profile.getLastName(), profile.getUser().getRole());
    }
}
