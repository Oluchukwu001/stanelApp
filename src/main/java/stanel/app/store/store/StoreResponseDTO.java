package stanel.app.store.store;

public record StoreResponseDTO(
    Long id,
    String name,
    String phoneNumber,
    String email,
    String address
) {
    
}
