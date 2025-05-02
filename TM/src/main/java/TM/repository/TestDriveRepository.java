package TM.repository;

import TM.model.ServiceRequest;
import TM.model.TestDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDrive, Integer> {
    List<TestDrive> findByIsDoneFalse();
    List<TestDrive> findByIsDoneTrue();
}
