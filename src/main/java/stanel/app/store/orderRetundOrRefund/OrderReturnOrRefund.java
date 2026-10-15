package stanel.app.store.orderRetundOrRefund;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.order.Order;
import stanel.app.store.orderItem.OrderItem;
import stanel.app.store.store.Store;
import stanel.app.store.util.BaseEntity;

@Entity 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class OrderReturnOrRefund extends BaseEntity{
    @Lob 
    private String reason;
    @OneToOne 
    @JoinColumn (name = "order_return/refund")
    private Order order;
    @OneToOne 
    @JoinColumn (name = "order_item_return/refund")
    private OrderItem orderItem;
    @ManyToOne ()
    private Store store;
}
