package stanel.app.store.profile.dto;

import java.util.List;

import stanel.app.store.user.enums.Role;

public record ProfileRequestDTO(
        String firstName, 
        String lastName,
        List<Long> storeIds,
        String phoneNumber,
        String email,
        Role role,
        String gender,
        String password,
        Long companyID
) {

}
