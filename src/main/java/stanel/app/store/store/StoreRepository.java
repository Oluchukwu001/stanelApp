package stanel.app.store.store;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stanel.app.store.order.Order;

@Repository 
public interface StoreRepository extends JpaRepository<Store,Long> {
    Page<Store> findAll(Pageable pageable);

    @Query ("""
            SELECT o  FROM Store s JOIN s.orders o WHERE s.id= :id ORDER BY o.createdAt DESC
            """)
    Page<Order> findOrdersByStoreIdAndOrderBycurrent(@Param ("id")Long id,Pageable pageable);
}
