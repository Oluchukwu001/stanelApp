package stanel.app.store.passwordreset;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgetPasswordRquestDTO(
    @NotBlank 
    @Email 
    String email
) {
    
}
