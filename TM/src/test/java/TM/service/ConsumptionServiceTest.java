package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import TM.model.Consumption;
import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.repository.ConsumptionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumptionServiceTest {

  @Mock private ConsumptionRepository consumptionRepository;

  @InjectMocks private ConsumptionService consumptionService;

  @Test
  void getConsumptionForModelAndEngineWhenNoData() {
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.PETROL_2_0_TSX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;

    when(consumptionRepository.findByModelTypeAndEngineType(modelType, engineType))
        .thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                consumptionService.getConsumptionForModelAndEngine(
                    modelType, engineType, consumptionType));

    assertEquals("Consumption not found", exception.getMessage());
  }

  @Test
  void getConsumptionForModelAndEngineWhenDataExists() {
    ModelType modelType = ModelType.TERAON;
    EngineType engineType = EngineType.PETROL_2_0_TSX;
    ConsumptionType consumptionType = ConsumptionType.MIXED;
    Consumption consumption = new Consumption();
    consumption.setMixedConsumption(7.0);

    when(consumptionRepository.findByModelTypeAndEngineType(modelType, engineType))
        .thenReturn(Optional.of(consumption));

    double result =
        consumptionService.getConsumptionForModelAndEngine(modelType, engineType, consumptionType);

    assertEquals(7.0, result);
  }

  @Test
  void getAllConsumptionsDetailsWhenNoData() {
    when(consumptionRepository.findAll()).thenReturn(Collections.emptyList());

    List<Consumption> result = consumptionService.getAllConsumptionsDetails();

    assertTrue(result.isEmpty());
  }

  @Test
  void getAllConsumptionsDetailsWhenDataExists() {
    Consumption consumption = new Consumption();
    consumption.setUrbanConsumption(8.0);

    List<Consumption> consumptionList = new ArrayList<>();
    consumptionList.add(consumption);

    when(consumptionRepository.findAll()).thenReturn(consumptionList);

    List<Consumption> result = consumptionService.getAllConsumptionsDetails();

    assertEquals(1, result.size());
  }
}
