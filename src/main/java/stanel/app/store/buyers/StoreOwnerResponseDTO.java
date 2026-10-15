package stanel.app.store.buyers;

import java.util.List;

import stanel.app.store.store.StoreResponseDTO;

public record StoreOwnerResponseDTO(
    Long id,
    String name,
    String email,
    String phoneNumber,
    List<StoreResponseDTO> storeResponseDTos
) {

}
