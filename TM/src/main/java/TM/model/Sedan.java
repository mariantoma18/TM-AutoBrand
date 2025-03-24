package TM.model;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sedan extends Car{

    private int startingPrice;
    private int finalPrice;
}
