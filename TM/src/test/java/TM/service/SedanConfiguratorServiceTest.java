package TM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import TM.model.Dto.SedanDto;
import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import TM.model.Form.CarConfiguratorForm;
import TM.model.Sedan;
import TM.repository.SedanRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SedanConfiguratorServiceTest {

  @Mock private SedanRepository sedanRepository;

  @InjectMocks private SedanConfiguratorService sedanConfiguratorService;

  @Test
  void createSedan() {
    CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
    carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
    carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
    carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

    Sedan sedan = new Sedan();
    sedan.setId(1);
    sedan.setFinalPrice(25000);

    when(sedanRepository.save(any(Sedan.class))).thenReturn(sedan);

    SedanDto sedanDto = sedanConfiguratorService.createSedan(carConfiguratorForm);

    assertEquals(27950, sedanDto.getFinalPrice());
  }

  @Test
  void mapToSedanDto() {
    CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
    carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
    carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
    carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

    SedanDto sedanDto = sedanConfiguratorService.mapToSedanDto(carConfiguratorForm);

    assertEquals(ExteriorColor.BLACK, sedanDto.getExteriorColor());
  }

  @Test
  void mapToSedan() {
    CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
    carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
    carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
    carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

    Sedan sedan = sedanConfiguratorService.mapToSedan(carConfiguratorForm);

    assertEquals(ExteriorColor.BLACK, sedan.getExteriorColor());
  }

  @Test
  void calculateOptionsPrice() {
    int exteriorPrice = 2500;
    int interiorPrice = 1000;
    int enginePrice = 2500;

    int result =
        sedanConfiguratorService.calculateOptionsPrice(exteriorPrice, interiorPrice, enginePrice);

    assertEquals(6000, result);
  }

  @Test
  void calculateTotalPrice() {
    CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
    carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
    carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
    carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

    SedanDto sedanDto = new SedanDto();

    int result = sedanConfiguratorService.calculateTotalPrice(carConfiguratorForm, sedanDto);

    assertEquals(27950, result);
  }

  @Test
  void getSedanByIdWhenNoData() {

    int sedanId = 1;

    when(sedanRepository.findById(sedanId)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> sedanConfiguratorService.getSedanById(sedanId));

    assertEquals("Sedan with id 1 does not exist", exception.getMessage());
  }

  @Test
  void getSedanByIdWhenDataExists() {

    int sedanId = 1;

    when(sedanRepository.findById(sedanId)).thenReturn(Optional.of(new Sedan()));

    Sedan sedan = sedanConfiguratorService.getSedanById(sedanId);

    assertEquals(new Sedan(), sedan);
  }
}
