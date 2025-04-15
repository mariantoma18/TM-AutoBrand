package TM.model.Form;

import lombok.Data;

@Data
public class ContactForm {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String emailSubject;
    private String emailContent;
}
