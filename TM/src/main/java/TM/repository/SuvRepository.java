package TM.repository;

import TM.model.Suv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuvRepository extends JpaRepository<Suv,Integer> {}
