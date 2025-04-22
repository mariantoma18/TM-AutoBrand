package TM.repository;

import TM.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoriesRepository extends JpaRepository<Accessory, Integer> {

    public List<Accessory> findByAccessoryCategoryId(int categoryId);
}
