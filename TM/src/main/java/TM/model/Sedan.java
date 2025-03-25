package TM.model;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sedans")
@NoArgsConstructor
public class Sedan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ExteriorColor exteriorColor;

    @Enumerated(EnumType.STRING)
    private InteriorColor interiorColor;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private final int startingPrice = 25000;
    private int finalPrice;

    public Sedan(ExteriorColor exteriorColor, InteriorColor interiorColor, EngineType engineType) {
        this.exteriorColor = exteriorColor;
        this.interiorColor = interiorColor;
        this.engineType = engineType;
    }
}
