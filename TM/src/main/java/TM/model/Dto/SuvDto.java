package TM.model.Dto;
import TM.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuvDto extends Car {

    private final int startingPrice = 28000;
    private int finalPrice;
}
