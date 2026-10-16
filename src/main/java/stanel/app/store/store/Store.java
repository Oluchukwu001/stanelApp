package stanel.app.store.store;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.cart.Cart;
import stanel.app.store.order.Order;
import stanel.app.store.profile.Profile;
import stanel.app.store.util.BaseEntity;

@Entity 
@Setter 
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Store extends BaseEntity {
    private String name;
    @Column (unique = true)
    @Email 
    private String email;
    @Column (unique = true)
    private String phoneNumber;
    private String address;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "storeOwner_id")
    private Profile storeOwner;
    @OneToMany (mappedBy = "store",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;
    
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "store_cart_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "profile_id")
    private Profile profile;
}
