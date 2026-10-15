package stanel.app.store.orderItem;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {

    private Long id;

    private Long productId;

    private BigDecimal price;

    private Integer quantity;

    private String imageUrl;

    public static OrderItemResponseDTO mapToDTO(OrderItem orderItem){
        String imageUrl = orderItem.getProduct().getImageUrls().size() > 0 ? orderItem.getProduct().getImageUrls().get(0) : "";
        return new OrderItemResponseDTO(
            orderItem.getId(),
         orderItem.getProduct().getId(),
         orderItem.getPrice(),
         orderItem.getQuantity(),
         imageUrl
        );
    }
}