package stanel.app.store.product;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.cartItem.CartItem;
import stanel.app.store.util.BaseEntity;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Product extends BaseEntity {
    private String name;
    private String size;
    private Integer quantity;
    private ProductStatus status;
    private BigDecimal price;
    private String description;
    @ElementCollection
    private List<String> imageUrls;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private CartItem cartItem;

    public Product(String name, String size, Integer quantity, ProductStatus status, BigDecimal price,
            String description, List<String> imageUrls, CartItem cartItem) {
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
        this.description = description;
        this.imageUrls = imageUrls;
        this.cartItem = cartItem;
    }

}
