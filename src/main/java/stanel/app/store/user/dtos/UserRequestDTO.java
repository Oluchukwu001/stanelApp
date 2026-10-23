package stanel.app.store.user.dtos;

import stanel.app.store.user.enums.Role;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        Role role,
        String password) {
}