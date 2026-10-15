package stanel.app.store.buyers;

import java.util.List;

import stanel.app.store.user.enums.Role;

public record StoreOwnerRequestDTO(
    String name,
    List<Long> storeIds,
    String phoneNumber,
    String email,
    Role role,
    String gender,
    String password
) {
    
}
