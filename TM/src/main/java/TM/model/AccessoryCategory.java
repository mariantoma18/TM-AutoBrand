package TM.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table (name="accessory_category")
public class AccessoryCategory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "accessoryCategory")
    private List<Accessory> accessories;
}
