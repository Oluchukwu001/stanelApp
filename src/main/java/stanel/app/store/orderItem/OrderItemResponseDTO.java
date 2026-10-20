package stanel.app.store.orderItem;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemResponseDTO {

    private Long id;

    private Long productId;

    private BigDecimal price;

    private Integer quantity;

    private String imageUrl;

    public OrderItemResponseDTO(Long id, Long productId, BigDecimal price, Integer quantity, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public static OrderItemResponseDTO mapToDTO(OrderItem orderItem) {
        String imageUrl = orderItem.getProduct().getImageUrls().size() > 0
                ? orderItem.getProduct().getImageUrls().get(0)
                : "";
        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                imageUrl);
    }
}