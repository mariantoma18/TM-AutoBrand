package TM.model.Form;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TestDriveForm {

  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{error.firstName.pattern}")
  @NotBlank(message = "{error.firstName.required}")
  @Size(min = 2, message = "{error.firstName.size}")
  private String firstName;

  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{error.lastName.pattern}")
  @NotBlank(message = "{error.lastName.required}")
  @Size(min = 2, message = "{error.lastName.size}")
  private String lastName;

  @NotBlank(message = "{error.email.required}")
  @Email(message = "{error.email.email}")
  private String email;

  @NotBlank(message = "{error.phoneNumber.required}")
  @Pattern(regexp = "^0\\d{9}$", message = "{error.phoneNumber.pattern}")
  private String phoneNumber;

  private LocalDateTime dateTime;
  private ModelType modelType;
  private EngineType engineType;
  private DealershipLocation dealershipLocation;
}
