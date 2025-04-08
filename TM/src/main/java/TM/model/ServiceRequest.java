package TM.model;

import TM.model.Enums.ModelType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="serviceRequests")
@NoArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    public ServiceRequest(String firstName, String lastName, String email, String phoneNumber, ModelType modelType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.modelType = modelType;
    }
}
