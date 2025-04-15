package TM.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="contact")
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String emailSubject;
    private String emailContent;

    public Contact(String firstName, String lastName, String email, String phoneNumber, String emailSubject, String emailContent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }
}
