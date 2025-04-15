package TM.repository;

import TM.model.Consumption;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Integer> {

    public Optional<Consumption> findByModelTypeAndEngineType(ModelType modelType, EngineType engineType);
}
