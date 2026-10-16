package stanel.app.store.passwordreset;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stanel.app.store.profile.Profile;
import stanel.app.store.util.BaseEntity;

@Entity
@Table(name = "password_rest")
@Getter
@Setter
@NoArgsConstructor
public class PasswordReset extends BaseEntity {
    @Column(nullable = false, unique = true, length = 64)
    private String token;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    boolean used = false;

    @ManyToOne (fetch = FetchType.LAZY,optional = false)
    @JoinColumn (name = "profile_id",nullable = false)
    private Profile profile;
}
