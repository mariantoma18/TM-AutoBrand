package TM.model.Enums;

import lombok.Getter;

@Getter
public enum ModelType {
  ELYSSION("Elyssion"),
  TERAON("Teraon");

  private final String description;

  ModelType(String description) {
    this.description = description;
  }
}
