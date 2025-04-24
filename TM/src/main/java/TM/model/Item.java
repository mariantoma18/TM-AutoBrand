package TM.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="items")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    private Accessory accessory;

    private int quantity;

    public Item(Accessory accessory, Cart cart, int quantity) {
        this.accessory = accessory;
        this.cart = cart;
        this.quantity=quantity;
    }
}
