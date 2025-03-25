package TM.model;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="suvs")
@NoArgsConstructor
public class Suv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ExteriorColor exteriorColor;

    @Enumerated(EnumType.STRING)
    private InteriorColor interiorColor;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private final int startingPrice = 28000;
    private int finalPrice;

    public Suv(ExteriorColor exteriorColor, InteriorColor interiorColor, EngineType engineType) {
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.engineType = engineType;
    }
}
