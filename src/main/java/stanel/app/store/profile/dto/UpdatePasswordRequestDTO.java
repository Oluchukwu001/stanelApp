package stanel.app.store.profile.dto;



import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequestDTO(
        @NotBlank(message = "Old password is required")
        String oldPassword,

        @NotBlank(message = "New password is required")
        String newPassword
) {
}
