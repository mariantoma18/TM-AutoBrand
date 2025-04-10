package TM.model.Form;
import TM.model.Enums.DealershipLocation;
import TM.model.Enums.ModelType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServiceRequestForm {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateTime;
    private ModelType modelType;
    private DealershipLocation dealershipLocation;
}
