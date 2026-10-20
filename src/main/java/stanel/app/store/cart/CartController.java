package stanel.app.store.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/cart")

public class CartController {

    private final CartServices cartServices;

    

    public CartController(CartServices cartServices) {
        this.cartServices = cartServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartDTO addToCart(
            @RequestBody CartRequestDTO requestDTO) {

        return cartServices.addToCart(requestDTO);
    }

    @PatchMapping("/{cartId}/items/{cartItemId}")
    public CartDTO reduceOrRemoveFromCart(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId) {

        return cartServices.reduceOrRemoveFromCart(
                cartItemId,
                cartId);
    }
}