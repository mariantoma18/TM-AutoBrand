package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import TM.model.Form.OfferRequestForm;
import TM.model.OfferRequest;
import TM.model.Sedan;
import TM.model.Suv;
import TM.repository.OfferRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OfferRequestServiceTest {

  @Mock private OfferRequestRepository offerRequestRepository;

  @InjectMocks private OfferRequestService offerRequestService;

  @Test
  void mapSedanToOfferRequest() {
    OfferRequestForm offerRequestForm = new OfferRequestForm();
    offerRequestForm.setFirstName("Test");
    offerRequestForm.setLastName("MyTest");
    offerRequestForm.setEmail("test@gmail.com");
    offerRequestForm.setPhoneNumber("0722222222");

    Sedan sedan = new Sedan();
    sedan.setExteriorColor(ExteriorColor.BLACK);
    sedan.setInteriorColor(InteriorColor.WHITE);
    sedan.setEngineType(EngineType.PETROL_2_0_TSX);

    OfferRequest offer = offerRequestService.mapSedanToOfferRequest(offerRequestForm, sedan);

    when(offerRequestRepository.save(offer)).thenReturn(offer);

    assertEquals(ExteriorColor.BLACK, offer.getSedan().getExteriorColor());
  }

  @Test
  void mapSuvToOfferRequest() {
    OfferRequestForm offerRequestForm = new OfferRequestForm();
    offerRequestForm.setFirstName("Test");
    offerRequestForm.setLastName("MyTest");
    offerRequestForm.setEmail("test@gmail.com");
    offerRequestForm.setPhoneNumber("0733333333");

    Suv suv = new Suv();
    suv.setExteriorColor(ExteriorColor.RED);
    suv.setInteriorColor(InteriorColor.WHITE);
    suv.setEngineType(EngineType.PETROL_2_0_TSX);

    OfferRequest offer = offerRequestService.mapSuvToOfferRequest(offerRequestForm, suv);

    when(offerRequestRepository.save(offer)).thenReturn(offer);

    assertEquals(ExteriorColor.RED, offer.getSuv().getExteriorColor());
  }
}
