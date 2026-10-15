package stanel.app.store.cartItem;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.cart.Cart;
import stanel.app.store.product.Product;
import stanel.app.store.util.BaseEntity;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter 
public class CartItem extends BaseEntity{
    @OneToOne 
    @JoinColumn (name = "product_id")
    private  Product product;

    private Integer quantity;

    private BigDecimal price;
    
    @ManyToOne 
    @JoinColumn (name = "cart_item_id")
    private Cart cart;
}
