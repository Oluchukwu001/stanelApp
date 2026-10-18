package stanel.app.store.resetPassword;

import org.jspecify.annotations.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDTO(
    @NonNull 
    String token,
    @NotBlank @Email 
    String email
) {
    
}
