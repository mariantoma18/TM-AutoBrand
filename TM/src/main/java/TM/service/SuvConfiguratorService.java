package TM.service;

import TM.model.Form.CarConfiguratorForm;
import TM.model.Dto.SuvDto;
import TM.model.Suv;
import TM.repository.SuvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuvConfiguratorService {

  private final SuvRepository suvRepository;

  public SuvDto createSuv(CarConfiguratorForm configuratorForm) {
    SuvDto selectedSuvDto = mapToSuvDto(configuratorForm);
    Suv suv = mapToSuv(configuratorForm);

    selectedSuvDto.setFinalPrice(calculateTotalPrice(configuratorForm, selectedSuvDto));
    suv.setFinalPrice(selectedSuvDto.getFinalPrice());

    Suv savedSuv = suvRepository.save(suv);

    selectedSuvDto.setId(savedSuv.getId());

    suvRepository.save(suv);

    return selectedSuvDto;
  }

  public Suv mapToSuv(CarConfiguratorForm configuratorForm) {
    return new Suv(
        configuratorForm.getExteriorColor(),
        configuratorForm.getInteriorColor(),
        configuratorForm.getEngineType());
  }

  public SuvDto mapToSuvDto(CarConfiguratorForm configuratorForm) {
    SuvDto selectedSuvDto = new SuvDto();
    selectedSuvDto.setExteriorColor(configuratorForm.getExteriorColor());
    selectedSuvDto.setInteriorColor(configuratorForm.getInteriorColor());
    selectedSuvDto.setEngineType(configuratorForm.getEngineType());
    return selectedSuvDto;
  }

  public int calculateOptionsPrice(
      int exteriorOptionPrice, int interiorOptionPrice, int engineOptionPrice) {
    return exteriorOptionPrice + interiorOptionPrice + engineOptionPrice;
  }

  public int calculateTotalPrice(CarConfiguratorForm configuratorForm, SuvDto selectedSuvDto) {
    return selectedSuvDto.getStartingPrice()
            + calculateOptionsPrice(
            configuratorForm.getExteriorColor().getOptionPrice(),
            configuratorForm.getInteriorColor().getOptionPrice(),
            configuratorForm.getEngineType().getOptionPrice());
  }

  public Suv getSuvById(int id) {
    return suvRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("SUV with id " + id + " does not exist"));
  }
}
