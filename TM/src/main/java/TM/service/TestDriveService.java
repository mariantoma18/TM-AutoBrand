package TM.service;

import TM.model.Form.TestDriveForm;
import TM.model.TestDrive;
import TM.repository.TestDriveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDriveService {

    private final TestDriveRepository testDriveRepository;

    public TestDrive mapFormToTestDrive(TestDriveForm testDriveForm){
        TestDrive testDrive = new TestDrive(
                testDriveForm.getFirstName(),
                testDriveForm.getLastName(),
                testDriveForm.getEmail(),
                testDriveForm.getPhoneNumber(),
                testDriveForm.getDateTime(),
                testDriveForm.getModelType(),
                testDriveForm.getEngineType(),
                testDriveForm.getDealershipLocation());

        testDrive.setDateTime(testDriveForm.getDateTime());

        testDriveRepository.save(testDrive);

        return testDrive;
    }

}
