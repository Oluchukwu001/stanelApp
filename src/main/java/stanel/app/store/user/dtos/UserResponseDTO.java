package stanel.app.store.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import stanel.app.store.user.User;
import stanel.app.store.user.enums.Role;

@AllArgsConstructor 
@Data 
public class UserResponseDTO {
    private String email;
    private Role role;
    public static UserResponseDTO mapToDTO(User user){
        return new UserResponseDTO(user.getEmail(), user.getRole());
    }
}
