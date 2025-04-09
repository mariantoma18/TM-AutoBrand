package TM.model.Form;

import TM.model.Enums.EngineType;
import TM.model.Enums.ExteriorColor;
import TM.model.Enums.InteriorColor;
import lombok.Data;

@Data
public class CarConfiguratorForm {

    private ExteriorColor exteriorColor;
    private InteriorColor interiorColor;
    private EngineType engineType;

    private int finalPrice;
}
