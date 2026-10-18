package stanel.app.store.resetPassword;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.profile.Profile;
import stanel.app.store.util.BaseEntity;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class PasswordResetToken extends  BaseEntity{

    @Column (unique = true,nullable = false)
    private String tokenHash;

    @Column (nullable = false)
    private Instant  expiration;
    @Column (nullable = false)
    private boolean used;
    @ManyToOne (optional = false)
    private Profile profile;
    
}
