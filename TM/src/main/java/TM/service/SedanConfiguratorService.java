package TM.service;

import TM.model.CarConfiguratorForm;
import TM.model.Dto.SedanDto;
import TM.model.Sedan;
import TM.repository.SedanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedanConfiguratorService {

  private final SedanRepository sedanRepository;

  public SedanDto createSedan(CarConfiguratorForm configuratorForm) {
    SedanDto selectedSedanDto = mapToSedanDto(configuratorForm);
    Sedan sedan = mapToSedan(configuratorForm);

    selectedSedanDto.setFinalPrice(calculateTotalPrice(configuratorForm, selectedSedanDto));
    sedan.setFinalPrice(selectedSedanDto.getFinalPrice());

    sedanRepository.save(sedan);

    return selectedSedanDto;
  }

  public SedanDto mapToSedanDto(CarConfiguratorForm configuratorForm) {
    SedanDto selectedSedanDto = new SedanDto();
    selectedSedanDto.setExteriorColor(configuratorForm.getExteriorColor());
    selectedSedanDto.setInteriorColor(configuratorForm.getInteriorColor());
    selectedSedanDto.setEngineType(configuratorForm.getEngineType());
    return selectedSedanDto;
  }

  public Sedan mapToSedan(CarConfiguratorForm configuratorForm) {
    return new Sedan(
        configuratorForm.getExteriorColor(),
        configuratorForm.getInteriorColor(),
        configuratorForm.getEngineType());
  }

  public int calculateOptionsPrice(
      int exteriorOptionPrice, int interiorOptionPrice, int engineOptionPrice) {
    return exteriorOptionPrice + interiorOptionPrice + engineOptionPrice;
  }

  public int calculateTotalPrice(CarConfiguratorForm configuratorForm, SedanDto selectedSedanDto) {
    return selectedSedanDto.getStartingPrice()
            + calculateOptionsPrice(
            configuratorForm.getExteriorColor().getOptionPrice(),
            configuratorForm.getInteriorColor().getOptionPrice(),
            configuratorForm.getEngineType().getOptionPrice());
  }
}
