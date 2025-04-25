package TM.controller;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.ModelType;
import TM.model.Form.ServiceRequestForm;
import TM.model.ServiceRequest;
import TM.service.ServiceRequestMailSenderService;
import TM.service.ServiceRequestService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceRequestController {

  private final ServiceRequestService serviceRequestService;
  private final ServiceRequestMailSenderService senderService;

  @GetMapping
  public String getServiceRequestFormPage(Model model) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    String nowFormatted = LocalDateTime.now().format(formatter);
    model.addAttribute("now", nowFormatted);
    model.addAttribute("serviceRequestForm", new ServiceRequestForm());
    model.addAttribute("modelTypes", ModelType.values());
    model.addAttribute("dealershipLocations", DealershipLocation.values());
    return "serviceRequestForm";
  }

  @PostMapping
  public String sendServiceForm(
      @ModelAttribute @Valid ServiceRequestForm serviceRequestForm,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
      String nowFormatted = LocalDateTime.now().format(formatter);
      model.addAttribute("now", nowFormatted);
      model.addAttribute("serviceRequestForm", serviceRequestForm);
      model.addAttribute("modelTypes", ModelType.values());
      model.addAttribute("dealershipLocations", DealershipLocation.values());
      return "serviceRequestForm";
    }

    ServiceRequest serviceRequest =
        serviceRequestService.mapFormToServiceRequest(serviceRequestForm);
    model.addAttribute("serviceRequest", serviceRequest);
    model.addAttribute("selectedDate", serviceRequestForm.getDateTime());
    senderService.sendEmailConfirmation(serviceRequest.getEmail(), serviceRequest);
    return "serviceRequestConfirmation";
  }
}
