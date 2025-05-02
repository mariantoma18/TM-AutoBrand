package TM.model;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "testDrive")
@NoArgsConstructor
public class TestDrive {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private LocalDateTime dateTime;
  private boolean isDone = false;

  @Enumerated(EnumType.STRING)
  private ModelType modelType;

  @Enumerated(EnumType.STRING)
  private EngineType engineType;

  @Enumerated(EnumType.STRING)
  private DealershipLocation dealershipLocation;

  public TestDrive(
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      LocalDateTime dateTime,
      ModelType modelType,
      EngineType engineType,
      DealershipLocation dealershipLocation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.dateTime = dateTime;
    this.modelType = modelType;
    this.engineType = engineType;
    this.dealershipLocation = dealershipLocation;
  }
}
