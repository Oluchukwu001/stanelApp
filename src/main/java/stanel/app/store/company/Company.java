package stanel.app.store.company;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.buyers.StoreOwner;
import stanel.app.store.staff.Staff;
import stanel.app.store.util.BaseEntity;

@Entity 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Company extends  BaseEntity{
    private String name;
    private String logoUrl;
    @ElementCollection 
    private List<String> ImageUrls;
    @Email (message = "Email required")
    @Column (unique = true,name = "company_email")
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany (mappedBy = "company", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Staff> companyWorkers;
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StoreOwner> storeOwners;
    
}