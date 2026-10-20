package stanel.app.store.user.dtos;

import lombok.Data;
import stanel.app.store.user.User;
import stanel.app.store.user.enums.Role;

@Data
public class UserResponseDTO {
    private String email;
    private Role role;

    public UserResponseDTO(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public static UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(user.getEmail(), user.getRole());
    }
}
