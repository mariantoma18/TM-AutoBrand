package TM.service;

import TM.model.OfferRequest;
import TM.model.OfferRequestForm;
import TM.model.Sedan;
import TM.repository.OfferRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OfferRequestService {

  private final OfferRequestRepository offerRequestRepository;

  public OfferRequest mapToOfferRequest(OfferRequestForm offerRequestForm, Sedan sedan) {
    OfferRequest offer =
        new OfferRequest(
            offerRequestForm.getFirstName(),
            offerRequestForm.getLastName(),
            offerRequestForm.getEmail(),
            offerRequestForm.getPhoneNumber(),
            sedan);

    offer.setDateTime(LocalDateTime.now());

    offerRequestRepository.save(offer);

    return offer;
  }
}
