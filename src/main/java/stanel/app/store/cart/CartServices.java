package stanel.app.store.cart;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import stanel.app.store.cartItem.CartItem;
import stanel.app.store.cartItem.CartItemRepository;
import stanel.app.store.exception.BadRequestException;
import stanel.app.store.product.Product;
import stanel.app.store.product.ProductRepository;
import stanel.app.store.store.Store;
import stanel.app.store.store.StoreRepository;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
public class CartServices {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final StoreRepository storeRepository;
    private final CartItemRepository cartItemRepository;

    
   

    public CartServices(ProductRepository productRepository, CartRepository cartRepository,
            StoreRepository storeRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.storeRepository = storeRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public CartDTO addToCart(CartRequestDTO requestDTO) {
        Store store = ServiceUtil.findOrThrow(storeRepository, requestDTO.storeId(), "Store");
        Product product = ServiceUtil.findOrThrow(productRepository, requestDTO.productId(), "Product");
        // CREATE NEW CART IF STORE DOESN'T HAVE A CART

        Cart cart = getOrCreateCart(store);
       
        // ADD PRODUCT TO CART IF THE PRODUCT IS NOT IN CART
        Optional<CartItem> existing = findCartItem(cart, product.getId());
        if (existing.isPresent()) {
            // Product already in cart -> quantity + 1 (dirty checking persists it)
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + 1);
        } else {
            CartItem item = newCartItem(cart, product);
            cart.getCartItems().add(item);
            cartItemRepository.save(item);
        }
        recalculateTotals(cart);
        cartRepository.save(cart);
        return CartDTO.mapCartDTO(cart);

    }

    @Transactional
    public CartDTO reduceOrRemoveFromCart(Long cartItemId, Long cartId) {
        ValidationUtil.requirePositive(cartItemId, "Cart item ID must be positive");
        Cart cart = ServiceUtil.findOrThrow(cartRepository, cartId, "Cart");
        CartItem cartItem = ServiceUtil.findOrThrow(cartItemRepository, cartItemId, "CartItem");
        if (!cart.getId().equals(cartItem.getCart().getId())) {
            throw new BadRequestException("Cart item doesn't belong to this cart");
        }
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        }
        recalculateTotals(cart);
        cartRepository.save(cart);
        return CartDTO.mapCartDTO(cart);

    }

    private Cart getOrCreateCart(Store store) {
        Cart cart = store.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setStore(store);
            store.setCart(cart);
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    private void recalculateTotals(Cart cart) {
        cart.setTotalPrice(calculateTotalPrice(cart));
        cart.setTotalQuantity(calculateTotalQuantity(cart));
    }

    private Integer calculateTotalQuantity(Cart cart) {
        return cart.getCartItems().stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getCartItems()) {
            BigDecimal price = item.getPrice();

            if (price != null && item.getQuantity() != null) {
            total = total.add(
                    price.multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );
        }
     }

        return total;
    }


    private Optional<CartItem> findCartItem(Cart cart, Long productId) {
        return cart.getCartItems().stream()
                .filter(el -> el.getProduct().getId().equals(productId))
                .findFirst();
    }

    private CartItem newCartItem(Cart cart, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setPrice(product.getPrice());
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        return cartItem;
    }
}
