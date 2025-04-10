package TM.model.Enums;

import lombok.Getter;

@Getter
public enum DealershipLocation {
    BRASOV ("Brasov, str. 13 Decembrie 133, 500199"),
    BUCHAREST ("Bucharest, str. Aeroportului 1, Sector 1");

    private final String description;

    DealershipLocation(String description) {
        this.description = description;
    }
}
