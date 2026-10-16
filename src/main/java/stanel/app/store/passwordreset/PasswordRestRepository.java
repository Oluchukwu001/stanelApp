
package stanel.app.store.passwordreset;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import stanel.app.store.profile.Profile;

public interface PasswordRestRepository extends JpaRepository<PasswordReset, Long> {

    Optional<PasswordReset> findByToken(String token);

    List<PasswordReset> findAllByProfileAndUsedFalse(Profile profile);

    Optional<Profile> findByUser_EmailIgnoreCase(String email);

}
