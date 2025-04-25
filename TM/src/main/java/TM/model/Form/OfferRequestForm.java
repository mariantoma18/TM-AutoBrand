package TM.model.Form;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OfferRequestForm {

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
}
