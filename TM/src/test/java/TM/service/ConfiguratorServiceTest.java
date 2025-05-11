package TM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import java.util.List;
import org.junit.jupiter.api.Test;

class ConfiguratorServiceTest {

  private final ConfiguratorService configuratorService = new ConfiguratorService();

  @Test
  void getAvailableExteriorColors() {
    List<ExteriorColor> list = configuratorService.getAvailableExteriorColors();

    assertEquals(5, list.size());
  }

  @Test
  void getAvailableInteriorColors() {
    List<InteriorColor> list = configuratorService.getAvailableInteriorColors();

    assertEquals(3, list.size());
  }

  @Test
  void getAvailableEngineTypes() {
    List<EngineType> list = configuratorService.getAvailableEngineTypes();

    assertEquals(2, list.size());
  }
}
