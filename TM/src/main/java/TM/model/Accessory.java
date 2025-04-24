package TM.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="accessories")
public class Accessory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;

    @ManyToOne
    private AccessoryCategory accessoryCategory;
}
