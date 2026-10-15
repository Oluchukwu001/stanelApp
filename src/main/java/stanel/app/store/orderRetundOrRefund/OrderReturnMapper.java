package stanel.app.store.orderRetundOrRefund;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper (componentModel = "spring")
public interface OrderReturnMapper {
    @Mapping (source = "order.id", target = "orderId")
    @Mapping (source = "store.id", target = "storeId")
    @Mapping (source = "orderItem.id", target = "orderItemId")
    OrderReturnResponseDTO toOrderReturnResponseDTO(OrderReturnOrRefund refund);
}
