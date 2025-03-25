package TM.model;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sedan extends Car{

    private final int startingPrice = 25000;
    private int finalPrice;
}
