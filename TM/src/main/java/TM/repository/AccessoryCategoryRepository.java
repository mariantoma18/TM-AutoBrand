package TM.repository;

import TM.model.AccessoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryCategoryRepository extends JpaRepository<AccessoryCategory,Integer> {

}
