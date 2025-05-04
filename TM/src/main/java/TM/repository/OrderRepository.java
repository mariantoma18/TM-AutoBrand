package TM.repository;

import TM.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByIsDoneFalse();
    List<Order> findByIsDoneTrue();
    long countByIsDoneFalse();
    long countByIsDoneTrue();
}
