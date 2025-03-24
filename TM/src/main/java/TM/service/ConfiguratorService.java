package TM.service;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import TM.model.Enums.InteriorColor;
import org.springframework.stereotype.Service;

@Service
public class ConfiguratorService {

  public List<ExteriorColor> getAvailableExteriorColors() {
    return new ArrayList<>(Arrays.asList(ExteriorColor.values()));
  }

  public List<InteriorColor> getAvailableInteriorColors(){
    return new ArrayList<>(Arrays.asList(InteriorColor.values()));
  }

  public List<EngineType> getAvailableEngineTypes(){
    return new ArrayList<>(Arrays.asList(EngineType.values()));
  }
}
