package stanel.app.store.orderRetundOrRefund;

public record OrderReturnOrRefundRequestDTO(
    String reasons,
    Long storeId,
    Long orderId,
    Long orderItemId
) {
    
}
