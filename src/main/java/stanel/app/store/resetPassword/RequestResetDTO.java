package stanel.app.store.resetPassword;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestResetDTO(
    @NotBlank @Email 
    String email
) {
    
}

