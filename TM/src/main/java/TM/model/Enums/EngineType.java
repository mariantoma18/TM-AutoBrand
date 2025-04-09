package TM.model.Enums;

import lombok.Getter;

@Getter
public enum EngineType {
        PETROL_2_0_TSX ("PETROL - 2.0 TSX EVO", 2000),
        DIESEL_2_0_TDX("DIESEL - 2.0 TDX ECO", 2500);

        private final String description;
        private final int optionPrice;

    EngineType(String description, int optionPrice) {
        this.description = description;
        this.optionPrice = optionPrice;
    }
}
