package stanel.app.store.orderRetundOrRefund;

public record OrderReturnResponseDTO(
    Long id,
    String reason,
    Long orderId,
    Long orderItemId,
    Long storeId
) {
    
}
