package TM.service;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.model.FuelPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumptionCalculatorService {

  private final ConsumptionService consumptionService;
  private final FuelPriceService fuelPriceService;

  public Double calculateFuelConsumptionInLiters(
      Long monthlyKms,
      ModelType modelType,
      EngineType engineType,
      ConsumptionType consumptionType) {

    if (monthlyKms == null || monthlyKms <= 0) {
      throw new RuntimeException("Monthly kilometers must be greater than zero.");
    }

    Double consumption =
        consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType);

    if (consumption == null || consumption <= 0) {
      throw new RuntimeException("Consumption data is invalid.");
    }

    return Math.round(((monthlyKms * consumption) / 100) * 100) / 100.0;
  }

  public Double calculateFuelCost(
      Long monthlyKms,
      ModelType modelType,
      EngineType engineType,
      ConsumptionType consumptionType) {

    Double price = 0.0;
    Double fuelUsed =
        calculateFuelConsumptionInLiters(monthlyKms, modelType, engineType, consumptionType);

    FuelPrice fuelPrice = fuelPriceService.getFuelPrice();

    if (fuelPrice == null) {
      throw new RuntimeException("Fuel price information is not available.");
    }

    if (engineType.name().contains("PETROL")) {
      price = Double.parseDouble(fuelPrice.getPetrolPrice());
    } else if (engineType.name().contains("DIESEL")) {
      price = Double.parseDouble(fuelPrice.getDieselPrice());
    }
    return Math.round(fuelUsed * price * 100) / 100.0;
  }
}
