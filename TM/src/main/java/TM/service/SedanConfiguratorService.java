package TM.service;

import TM.model.Car;
import TM.model.CarConfiguratorForm;
import TM.model.Sedan;
import org.springframework.stereotype.Service;

@Service
public class SedanConfiguratorService {

  public Sedan createSedan(CarConfiguratorForm configuratorForm) {
    Sedan selectedSedan = new Sedan();
    selectedSedan.setExteriorColor(configuratorForm.getExteriorColor());
    selectedSedan.setInteriorColor(configuratorForm.getInteriorColor());
    selectedSedan.setEngineType(configuratorForm.getEngineType());

    return selectedSedan;
  }
}
