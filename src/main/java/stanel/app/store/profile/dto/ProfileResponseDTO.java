package stanel.app.store.profile.dto;

import java.util.List;

import lombok.Data;
import stanel.app.store.profile.Profile;
import stanel.app.store.user.enums.Role;

@Data
public class ProfileResponseDTO {

    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Role role;

    public ProfileResponseDTO(String email, String phoneNumber, String firstName, String lastName, Role role) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public static ProfileResponseDTO mapToDTO(Profile profile) {
        return new ProfileResponseDTO(profile.getUser().getEmail(), profile.getPhoneNumber(), profile.getFirstName(),
                profile.getLastName(), profile.getUser().getRole());
    }

    public static List<ProfileResponseDTO> mapToDtos(List<Profile> profiles) {
        return profiles.stream().map(ProfileResponseDTO::mapToDTO).toList();
    }
}
