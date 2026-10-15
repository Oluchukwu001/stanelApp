package stanel.app.store.cart;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import stanel.app.store.cartItem.CartItemDTO;

@Data 
@AllArgsConstructor 
public class CartDTO{
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private List<CartItemDTO> items;
}