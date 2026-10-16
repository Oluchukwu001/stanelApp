package stanel.app.store.cart;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stanel.app.store.cartItem.CartItemDTO;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CartDTO{
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private List<CartItemDTO> items;


    public static  CartDTO mapCartDTO(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setItems(CartItemDTO.mapAllToDTO(cart.getCartItems()));
        cartDTO.setTotalQuantity(cart.getTotalQuantity());
        return cartDTO;
    }
}