package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.ModelType;
import TM.model.Form.ServiceRequestForm;
import TM.model.ServiceRequest;
import TM.repository.ServiceRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceRequestServiceTest {

  @Mock private ServiceRequestRepository serviceRequestRepository;

  @InjectMocks private ServiceRequestService serviceRequestService;

  @Test
  void mapFormToServiceRequest() {
    ServiceRequestForm serviceRequestForm = new ServiceRequestForm();
    serviceRequestForm.setFirstName("John");
    serviceRequestForm.setLastName("Snow");
    serviceRequestForm.setEmail("the_king_of_the_north@gmail.com");
    serviceRequestForm.setPhoneNumber("0711111111");
    serviceRequestForm.setModelType(ModelType.ELYSSION);
    serviceRequestForm.setDealershipLocation(DealershipLocation.BRASOV);

    ServiceRequest serviceRequest =
        new ServiceRequest(
            serviceRequestForm.getFirstName(),
            serviceRequestForm.getLastName(),
            serviceRequestForm.getEmail(),
            serviceRequestForm.getPhoneNumber(),
            serviceRequestForm.getModelType(),
            serviceRequestForm.getDealershipLocation());

    when(serviceRequestRepository.save(serviceRequest)).thenReturn(serviceRequest);

    ServiceRequest result = serviceRequestService.mapFormToServiceRequest(serviceRequestForm);

    assertEquals("the_king_of_the_north@gmail.com", serviceRequest.getEmail());
  }
}
