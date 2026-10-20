package stanel.app.store.resetPassword;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stanel.app.store.profile.Profile;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByTokenHash(String hashedToken);

    void deleteByProfile(Profile profile);

}
