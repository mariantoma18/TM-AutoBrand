package TM.model.Form;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarConfiguratorForm {

    @NotNull(message = "{error.exteriorColor.null}")
    private ExteriorColor exteriorColor;

    @NotNull(message = "{error.interiorColor.null}")
    private InteriorColor interiorColor;

    @NotNull(message = "{error.engineType.null}")
    private EngineType engineType;

    private int finalPrice;
}
