package stanel.app.store.buyers;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.store.Store;
import stanel.app.store.user.User;
import stanel.app.store.util.BaseEntity;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter
public class StoreOwner extends BaseEntity {
    
    
    @OneToMany (mappedBy = "storeOwner",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Store> stores;

    @OneToOne (cascade = CascadeType.ALL,optional = false)
    @JoinColumn (name = "user_id",unique = true,nullable = false)
    private User user;
}
