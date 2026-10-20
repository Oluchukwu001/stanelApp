package stanel.app.store.cart;
import java.math.BigDecimal;
import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;
import stanel.app.store.cartItem.CartItemDTO;

@Data 

@NoArgsConstructor 
public class CartDTO{
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private List<CartItemDTO> items;

    


    public CartDTO(BigDecimal totalPrice, Integer totalQuantity, List<CartItemDTO> items) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.items = items;
    }




    public static  CartDTO mapCartDTO(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setItems(CartItemDTO.mapAllToDTO(cart.getCartItems()));
        cartDTO.setTotalQuantity(cart.getTotalQuantity());
        return cartDTO;
    }
}