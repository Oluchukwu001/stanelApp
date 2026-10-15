package stanel.app.store.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.cartItem.CartItem;
import stanel.app.store.store.Store;
import stanel.app.store.util.BaseEntity;

@Entity 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
public class Cart extends BaseEntity{
    
    @OneToMany (mappedBy = "cart",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    
    private BigDecimal totalPrice;

    private Integer totalQuantity;

    @OneToOne (mappedBy = "cart")
    private Store store;
    
}
