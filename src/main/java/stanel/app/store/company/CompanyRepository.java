package stanel.app.store.company;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stanel.app.store.buyers.StoreOwner;
import stanel.app.store.staff.Staff;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
        @Query("""
                        SELECT s FROM Company c JOIN c.companyWorkers s WHERE c.id = :id ORDER BY s.role DESC
                        """)
        List<Staff> findStaffByCompanyIdOrderByRoleDesc(@Param("id") Long id);

        @Query("""
                        SELECT storeOwner FROM Company c JOIN c.storeOwners storeOwner  WHERE c.id = :id ORDER BY storeOwner.name
                        """)
        List<StoreOwner> findStoreOwnersByCompanyIdOrderByName(@Param("id") Long id);


        Page<Company> findAll(Pageable Pageable);

       Optional<Company> findByName(String companyName);

}
