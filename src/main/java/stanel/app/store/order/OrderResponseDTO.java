package stanel.app.store.order;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.orderItem.OrderItemResponseDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private Long id;

    private List<OrderItemResponseDTO> orderItems;

    private BigDecimal totalPrice;

    private Status status;

    private Integer totalQuantity;

    public static OrderResponseDTO mapToDTO(Order order) {

        List<OrderItemResponseDTO> orderItems = order.getOrderItems()
                .stream()
                .map(OrderItemResponseDTO::mapToDTO)
                .toList();

        return new OrderResponseDTO(
                order.getId(),
                orderItems,
                order.getTotalPrice(),
                order.getStatus(),
                order.getTotalQuantity()
        );
    }
}