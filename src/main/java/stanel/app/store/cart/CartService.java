package stanel.app.store.cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


import stanel.app.store.cartItem.CartItem;
import stanel.app.store.product.Product;
import stanel.app.store.store.Store;
import stanel.app.store.util.Resolver;


@Service

public class CartService {
    private final Resolver resolver;
    private final CartRepository cartRepository;

    

    public CartService(Resolver resolver, CartRepository cartRepository) {
        this.resolver = resolver;
        this.cartRepository = cartRepository;
    }

    public void addToCart(Long productId, Long storeId) {
        Product product = resolver.resolveProduct(productId);
        Store store = resolver.resolveStore(storeId);
        Cart storeCart = store.getCart();
        CartItem cartItem = getCartItem(storeCart, productId);
        if (cartItem.equals(null)) {
            storeCart.getCartItems().add(createCartItem(storeCart, product, 1));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        storeCart.setTotalQuantity(getCartTotalQuantity(storeCart));
        storeCart.setTotalPrice(getCartTotalPrice(storeCart));
        cartRepository.save(storeCart);
    }

    public void reduceQuantity(Long productId, Long storeId) {
        resolver.resolveProduct(productId);
        Store store = resolver.resolveStore(storeId);
        Cart storeCart = store.getCart();
        CartItem cartItem = getCartItem(storeCart, productId);
        if (!cartItem.equals(null)) {
            if (cartItem.getQuantity().equals(1)) {
                storeCart.getCartItems().remove(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            }
        }
        cartRepository.save(storeCart);
    }

    private CartItem getCartItem(Cart cart, Long productId) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private BigDecimal getCartTotalPrice(Cart cart) {
        return cart.getCartItems().stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Integer getCartTotalQuantity(Cart cart) {
        return cart.getCartItems().stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    private CartItem createCartItem(Cart cart, Product product, Integer quantity) {
        return new CartItem(product, quantity, product.getPrice(), cart);
    }
}
