package TM.model.Enums;

import lombok.Getter;

@Getter
public enum EngineType {
        PETROL_2_0_TSI ("PETROL - 2.0 TSI EVO"),
        DIESEL_1_6_TDI("DIESEL - 1.6 TDX ECO");

        private String description;

    EngineType(String description) {
        this.description = description;
    }
}
