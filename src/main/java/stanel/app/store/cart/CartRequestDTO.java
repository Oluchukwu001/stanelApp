package stanel.app.store.cart;



public record CartRequestDTO(
    Long productId,
    Integer quantity,
    Long storeId
) {
    
}
