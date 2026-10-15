package stanel.app.store.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stanel.app.store.user.enums.Role;

/**
 * ProfileRespository
 */
public interface ProfileRespository extends JpaRepository<Profile, Long> {
    List<Profile> findAllByRole(Role role);

    List<Profile> findAllByRoleIn(List<Role> role);
} 