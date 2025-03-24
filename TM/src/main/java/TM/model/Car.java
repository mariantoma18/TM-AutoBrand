package TM.model;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public abstract class Car {
    ExteriorColor exteriorColor;
    InteriorColor interiorColor;
    EngineType engineType;
}
