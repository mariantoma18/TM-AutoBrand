package TM.model.Enums;

import lombok.Getter;

@Getter
public enum InteriorColor {
    BLACK("Black", 350),
    WHITE("White", 400),
    TAN("Tan",500);

    private final String description;
    private final int optionPrice;

    InteriorColor(String description, int optionPrice) {
        this.description = description;
        this.optionPrice = optionPrice;
    }
}
