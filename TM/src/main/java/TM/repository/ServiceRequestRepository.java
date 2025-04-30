package TM.repository;

import TM.model.Order;
import TM.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
    List<ServiceRequest> findByIsDoneFalse();
    List<ServiceRequest> findByIsDoneTrue();
}
