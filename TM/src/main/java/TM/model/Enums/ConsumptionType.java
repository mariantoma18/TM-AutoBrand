package TM.model.Enums;

import lombok.Getter;

@Getter
public enum ConsumptionType {
  URBAN("Urban"),
  EXTRA_URBAN("Extra-Urban"),
  MIXED("Mixed");

  private final String description;

  ConsumptionType(String description) {
    this.description = description;
  }
}
