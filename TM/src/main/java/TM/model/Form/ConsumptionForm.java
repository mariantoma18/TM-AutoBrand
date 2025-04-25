package TM.model.Form;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsumptionForm {

    private ModelType modelType;
    private EngineType engineType;
    private ConsumptionType consumptionType;

    @NotNull(message = "{error.monthlyKms.required}")
    private Long monthlyKms;
}
