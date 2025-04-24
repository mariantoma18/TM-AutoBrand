package TM.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Cart(User user) {
        this.user = user;
    }
}
