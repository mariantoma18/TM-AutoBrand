package TM.repository;

import TM.model.Sedan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedanRepository extends JpaRepository<Sedan,Integer> {}
