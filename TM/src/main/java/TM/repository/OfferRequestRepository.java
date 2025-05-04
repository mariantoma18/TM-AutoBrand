package TM.repository;

import TM.model.OfferRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRequestRepository extends JpaRepository <OfferRequest,Integer>{
    long countBySedanIsNotNull();
    long countBySuvIsNotNull();
}
