package TM.service;

import TM.model.Dto.SuvDto;
import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import TM.model.Form.CarConfiguratorForm;
import TM.model.Suv;
import TM.repository.SuvRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SuvConfiguratorServiceTest {

    @Mock
    private SuvRepository suvRepository;

    @InjectMocks
    private SuvConfiguratorService suvConfiguratorService;

    @Test
    void createSuv() {
        CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
        carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
        carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
        carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

        Suv suv = new Suv();
        suv.setId(1);
        suv.setFinalPrice(25000);

        when(suvRepository.save(any(Suv.class))).thenReturn(suv);

        SuvDto suvDto = suvConfiguratorService.createSuv(carConfiguratorForm);

        assertEquals(30950, suvDto.getFinalPrice());
    }

    @Test
    void mapToSuvDto() {
        CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
        carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
        carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
        carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

        SuvDto suvDto = suvConfiguratorService.mapToSuvDto(carConfiguratorForm);

        assertEquals(ExteriorColor.BLACK, suvDto.getExteriorColor());
    }

    @Test
    void mapToSuv() {
        CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
        carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
        carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
        carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

        Suv suv = suvConfiguratorService.mapToSuv(carConfiguratorForm);

        assertEquals(ExteriorColor.BLACK, suv.getExteriorColor());
    }

    @Test
    void calculateOptionsPrice() {
        int exteriorPrice = 2500;
        int interiorPrice = 1000;
        int enginePrice = 2500;

        int result =
                suvConfiguratorService.calculateOptionsPrice(exteriorPrice, interiorPrice, enginePrice);

        assertEquals(6000, result);
    }

    @Test
    void calculateTotalPrice() {
        CarConfiguratorForm carConfiguratorForm = new CarConfiguratorForm();
        carConfiguratorForm.setExteriorColor(ExteriorColor.BLACK);
        carConfiguratorForm.setInteriorColor(InteriorColor.WHITE);
        carConfiguratorForm.setEngineType(EngineType.PETROL_2_0_TSX);

        SuvDto suvDto = new SuvDto();

        int result = suvConfiguratorService.calculateTotalPrice(carConfiguratorForm, suvDto);

        assertEquals(30950, result);
    }

    @Test
    void getSuvByIdWhenNoData() {

        int suvId = 1;

        when(suvRepository.findById(suvId)).thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> suvConfiguratorService.getSuvById(suvId));

        assertEquals("SUV with id 1 does not exist", exception.getMessage());
    }

    @Test
    void getSuvByIdWhenDataExists() {

        int suvId = 1;

        when(suvRepository.findById(suvId)).thenReturn(Optional.of(new Suv()));

        Suv suv = suvConfiguratorService.getSuvById(suvId);

        assertEquals(new Suv(), suv);
    }
}
