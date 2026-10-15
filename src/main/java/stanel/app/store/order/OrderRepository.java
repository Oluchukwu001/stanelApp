package stanel.app.store.order;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT o
            FROM Order o
            WHERE o.store.id = :storeId
            ORDER BY o.createdAt DESC
            """)
    Page<Order> findAllOrderByStoreId(@Param("storeId") Long storeId,Pageable pageable);
}
