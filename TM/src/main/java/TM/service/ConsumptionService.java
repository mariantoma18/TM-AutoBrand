package TM.service;

import TM.model.Consumption;
import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.repository.ConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionService {

  private final ConsumptionRepository consumptionRepository;
  private FuelPriceService fuelPriceService;

  public Double getConsumptionForModelAndEngine(
      ModelType modelType, EngineType engineType, ConsumptionType consumptionType) {

    Consumption consumption =
        consumptionRepository
            .findByModelTypeAndEngineType(modelType, engineType)
            .orElseThrow(() -> new RuntimeException("Consumption not found"));

    switch (consumptionType) {
      case MIXED -> {
        return consumption.getMixedConsumption();
      }
      case URBAN -> {
        return consumption.getUrbanConsumption();
      }
      case EXTRA_URBAN -> {
        return consumption.getExtraUrbanConsumption();
      }
    }

    return 0.0;
  }

  public List<Consumption> getAllConsumptionsDetails(){
    return consumptionRepository.findAll();
  }
}
