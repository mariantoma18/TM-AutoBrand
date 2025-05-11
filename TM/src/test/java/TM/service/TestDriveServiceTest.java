package TM.service;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.ModelType;
import TM.model.Form.ServiceRequestForm;
import TM.model.Form.TestDriveForm;
import TM.model.ServiceRequest;
import TM.model.TestDrive;
import TM.repository.ServiceRequestRepository;
import TM.repository.TestDriveRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TestDriveServiceTest {

  @Mock
  private TestDriveRepository testDriveRepository;

  @InjectMocks
  private TestDriveService testDriveService;

  @Test
  void mapFormToTestDriveRequest() {
    TestDriveForm testDriveForm = new TestDriveForm();
    testDriveForm.setFirstName("Ion");
    testDriveForm.setLastName("Moromete");
    testDriveForm.setEmail("ion@gmail.com");
    testDriveForm.setPhoneNumber("0711111111");
    testDriveForm.setModelType(ModelType.ELYSSION);
    testDriveForm.setModelType(ModelType.ELYSSION);
    testDriveForm.setDealershipLocation(DealershipLocation.BRASOV);

    TestDrive testDrive =
        new TestDrive();

    when(testDriveRepository.save(testDrive)).thenReturn(testDrive);

    TestDrive result = testDriveService.mapFormToTestDrive(testDriveForm);

    assertEquals("ion@gmail.com", result.getEmail());
  }
}