package TM.model.Enums;

import lombok.Getter;

@Getter
public enum ExteriorColor {
    BLACK("Obsidian Black" , 550),
    BLUE("Oceanic Blue", 550),
    GREEN("Forest Emerald", 600),
    RED("Volcano Red", 650),
    SILVER("Titanium Silver", 500);

    private final String description;
    private final int optionPrice;

    ExteriorColor(String description, int optionPrice) {
        this.description = description;
        this.optionPrice = optionPrice;
    }
}
