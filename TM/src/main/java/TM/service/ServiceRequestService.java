package TM.service;

import TM.model.ServiceRequest;
import TM.model.ServiceRequestForm;
import TM.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest mapFormToServiceRequest(ServiceRequestForm serviceRequestForm){
        ServiceRequest serviceRequest = new ServiceRequest(
                serviceRequestForm.getFirstName(),
                serviceRequestForm.getLastName(),
                serviceRequestForm.getEmail(),
                serviceRequestForm.getPhoneNumber(),
                serviceRequestForm.getModelType());

        serviceRequest.setDateTime(serviceRequestForm.getDateTime());

        serviceRequestRepository.save(serviceRequest);

        return serviceRequest;
    }
}
