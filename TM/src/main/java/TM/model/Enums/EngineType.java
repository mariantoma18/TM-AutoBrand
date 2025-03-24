package TM.model.Enums;

import lombok.Getter;

@Getter
public enum EngineType {
        PETROL_2_0_TSI ("PETROL - 2.0 TSI EVO", 25000),
        DIESEL_1_6_TDI("DIESEL - 1.6 TDX ECO", 28000);

        private final String description;
        private final int optionPrice;

    EngineType(String description, int optionPrice) {
        this.description = description;
        this.optionPrice = optionPrice;
    }
}
