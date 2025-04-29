package TM.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@RequiredArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne private User user;

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

  @NotBlank(message = "{error.deliveryAddress.required}")
  @Size(min = 5, message = "{error.deliveryAddress.size}")
  private String deliveryAddress;

  private double cartPrice;
  private double shippingCost;
  private double orderTotalPrice;

  private boolean isDone = false;

  public Order(
      User user,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String deliveryAddress,
      double cartPrice,
      double shippingCost) {
    this.user = user;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.deliveryAddress = deliveryAddress;
    this.cartPrice = cartPrice;
    this.shippingCost = shippingCost;
    this.orderTotalPrice = cartPrice + shippingCost;
  }
}
