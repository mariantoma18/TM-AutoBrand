package TM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "offers")
public class OfferRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

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

  @ManyToOne private Sedan sedan;

  @ManyToOne private Suv suv;

  public OfferRequest(
      String firstName, String lastName, String email, String phoneNumber, Sedan sedan) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.sedan = sedan;
  }

  public OfferRequest(
      String firstName, String lastName, String email, String phoneNumber, Suv suv) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.suv = suv;
  }
}
