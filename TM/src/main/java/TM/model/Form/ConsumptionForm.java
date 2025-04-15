package TM.model.Form;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ConsumptionForm {

    private ModelType modelType;
    private EngineType engineType;
    private ConsumptionType consumptionType;
    private Long monthlyKms;
}
