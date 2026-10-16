package stanel.app.store.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import stanel.app.store.user.enums.Role;

/**
 * ProfileRespository
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findAllByRole(Role role);

    List<Profile> findAllByRoleIn(List<Role> role);

    Optional<Profile> findByUser_EmailIgnoreCase(String email);
} 