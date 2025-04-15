package TM.model;

import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;
    
    private Double urbanConsumption;
    private Double extraUrbanConsumption;
    private Double mixedConsumption;
}
