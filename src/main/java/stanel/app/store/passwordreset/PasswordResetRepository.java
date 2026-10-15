package stanel.app.store.passwordreset;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordRest, Long> {
    Optional<PasswordRest> findByTokeHash(String tokenHash);

    void deleteByStaff_Id(Long Id);

}
