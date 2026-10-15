package stanel.app.store.store;

public record StoreRequestDTO(
    Long storeOwnerId,
    String name,
    String phoneNumber,
    String email,
    String address
) {
    
}
