package TM.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="orders")
@Data
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String deliveryAddress;
    private double cartPrice;
    private double shippingCost;
    private double orderTotalPrice;

    public Order(User user, String firstName, String lastName, String email, String phoneNumber, String deliveryAddress, double cartPrice, double shippingCost) {
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
