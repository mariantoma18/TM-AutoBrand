package TM.model.Form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OfferRequestForm {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateTime;
}
