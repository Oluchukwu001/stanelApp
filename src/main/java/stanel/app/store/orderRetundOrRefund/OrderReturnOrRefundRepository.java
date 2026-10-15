package stanel.app.store.orderRetundOrRefund;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface OrderReturnOrRefundRepository extends JpaRepository<OrderReturnOrRefund,Long>{
    
}
