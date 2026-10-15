package stanel.app.store.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query ("""
            SELECT p.name, COUNT(p) FROM Product p WHERE p.status = :status GROUP BY p.name
            """)
    List<Object[]> countProductsByNameAndStatus(@Param ("status")ProductStatus status);
    
    Page<Product> findAll(Pageable pageable);
}
