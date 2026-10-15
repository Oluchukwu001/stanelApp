package stanel.app.store.cartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartItemDTO {
    private Integer quantity;
    private BigDecimal price;
    private Long productId;

    public static CartItemDTO mapToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        return cartItemDTO;
    }
    public static List<CartItemDTO> mapAllToDTO(List<CartItem> cartItems) {
        List<CartItemDTO> newCartItemDTOs = new ArrayList<>();
        for(CartItem cartItem : cartItems) {
            newCartItemDTOs.add(mapToDTO(cartItem));
        }
        return newCartItemDTOs;
        
        
    
    }
}
