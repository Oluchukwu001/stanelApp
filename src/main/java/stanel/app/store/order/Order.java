package stanel.app.store.order;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.orderItem.OrderItem;
import stanel.app.store.store.Store;
import stanel.app.store.util.BaseEntity;


@Entity 
@Setter 
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
@Table (name = "Orders")
public class Order extends BaseEntity{
    @ManyToOne ()
    @JoinColumn (name = "store_id")
    private Store store;
    
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "order")
    private List<OrderItem> orderItems;
   
    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private Status status = Status.PENDING;

}

