package stanel.app.store.buyers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface StoreOwnerRepository extends JpaRepository<StoreOwner,Long>{
    Page<StoreOwner> findAll(Pageable pageable);
    Optional<StoreOwner> findStoreOwnerByEmail(String email);
}
