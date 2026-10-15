package stanel.app.store.cart;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServices cartServices;

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