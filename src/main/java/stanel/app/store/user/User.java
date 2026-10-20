package stanel.app.store.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.user.enums.Role;
import stanel.app.store.util.BaseEntity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    @Email
    @Column(unique = true)
    private String email;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STOREOWNER;

    public User(@Email String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    
}
