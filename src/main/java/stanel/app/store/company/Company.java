package stanel.app.store.company;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.profile.Profile;
import stanel.app.store.util.BaseEntity;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Company extends BaseEntity {
    private String name;
    private String logoUrl;
    @ElementCollection
    private List<String> ImageUrls;
    @Email(message = "Email required")
    @Column(unique = true, name = "company_email")
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> companyWorkers;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Profile> storeOwners;

    public Company(String name, String logoUrl, List<String> imageUrls, @Email(message = "Email required") String email,
            String phoneNumber, String address, List<Profile> companyWorkers, List<Profile> storeOwners) {
        this.name = name;
        this.logoUrl = logoUrl;
        ImageUrls = imageUrls;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.companyWorkers = companyWorkers;
        this.storeOwners = storeOwners;
    }

}