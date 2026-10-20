package stanel.app.store.orderItem;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.order.Order;
import stanel.app.store.product.Product;
import stanel.app.store.util.BaseEntity;

@Entity

@NoArgsConstructor
@Setter
@Getter
@Table(name = "Order_itmes")
public class OrderItem extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(Product product, Order order, BigDecimal price, Integer quantity) {
        this.product = product;
        this.order = order;
        this.price = price;
        this.quantity = quantity;
    }

}
