package stanel.app.store.orderItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface OrderitemRepository extends JpaRepository<OrderItem,Long> {
    Page<OrderItem> findAll(Pageable pageable);
}
