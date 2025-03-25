package TM.model.Dto;
import TM.model.Car;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedanDto extends Car {

    private final int startingPrice = 25000;
    private int finalPrice;
    private int id;
}
