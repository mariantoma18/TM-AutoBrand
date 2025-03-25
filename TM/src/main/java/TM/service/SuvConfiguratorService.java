package TM.service;

import TM.model.CarConfiguratorForm;
import TM.model.Suv;
import org.springframework.stereotype.Service;

@Service
public class SuvConfiguratorService {

    public Suv createSuv(CarConfiguratorForm configuratorForm) {
        Suv selectedSuv = new Suv();
        selectedSuv.setExteriorColor(configuratorForm.getExteriorColor());
        selectedSuv.setInteriorColor(configuratorForm.getInteriorColor());
        selectedSuv.setEngineType(configuratorForm.getEngineType());

        selectedSuv.setFinalPrice(
                calculateTotalPrice(
                        configuratorForm.getExteriorColor().getOptionPrice(),
                        configuratorForm.getInteriorColor().getOptionPrice(),
                        configuratorForm.getEngineType().getOptionPrice()));

        return selectedSuv;
    }

    public int calculateTotalPrice(
            int exteriorOptionPrice, int interiorOptionPrice, int engineOptionPrice) {
        return exteriorOptionPrice + interiorOptionPrice + engineOptionPrice;
    }
}
