package TM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.model.FuelPrice;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumptionCalculatorServiceTest {

  @Mock private ConsumptionService consumptionService;

  @Mock private FuelPriceService fuelPriceService;

  @InjectMocks private ConsumptionCalculatorService consumptionCalculatorService;

  @Test
  void testCalculateFuelConsumptionInLitersWhenNoMonthlyKmsData() {
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.DIESEL_2_0_TDX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    when(consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType))
            .thenReturn(8.0);

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                consumptionCalculatorService.calculateFuelConsumptionInLiters(
                    0L, modelType, engineType, consumptionType));

    assertEquals("Monthly kilometers must be greater than zero.", exception.getMessage());
  }

  @Test
  void testCalculateFuelConsumptionInLitersWhenNoConsumptionData() {
    long monthlyKms = 100;
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.DIESEL_2_0_TDX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    when(consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType))
        .thenReturn(null);

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                consumptionCalculatorService.calculateFuelConsumptionInLiters(
                    monthlyKms, modelType, engineType, consumptionType));

    assertEquals("Consumption data is invalid.", exception.getMessage());
  }

  @Test
  void calculateFuelConsumptionInLiters() {
    long monthlyKms = 100;
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.PETROL_2_0_TSX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    when(consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType))
        .thenReturn(8.0);

    double consumptionInLiters =
        consumptionCalculatorService.calculateFuelConsumptionInLiters(
            monthlyKms, modelType, engineType, consumptionType);

    assertEquals(8, consumptionInLiters);
  }

  @Test
  void testCalculateFuelCostWithValidData() {
    long monthlyKms = 100;
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.DIESEL_2_0_TDX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    FuelPrice fuelPrice = new FuelPrice("8.0", "6.0");

    when(consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType))
        .thenReturn(6.0);
    when(fuelPriceService.getFuelPrice()).thenReturn(fuelPrice);

    Double result =
        consumptionCalculatorService.calculateFuelCost(
            monthlyKms, modelType, engineType, consumptionType);

    assertEquals(36, result);
  }

  @Test
  void testCalculateFuelCostWhenNoData() {
    long monthlyKms = 100;
    ModelType modelType = ModelType.ELYSSION;
    EngineType engineType = EngineType.PETROL_2_0_TSX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    when(consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType))
        .thenReturn(6.0);
    when(fuelPriceService.getFuelPrice()).thenReturn(null);

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                consumptionCalculatorService.calculateFuelCost(
                    monthlyKms, modelType, engineType, consumptionType));

    assertEquals("Fuel price information is not available.", exception.getMessage());
  }
}
