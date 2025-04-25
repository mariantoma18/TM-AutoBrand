package TM.controller;

import TM.model.Enums.DealershipLocation;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.model.Form.TestDriveForm;
import TM.service.TestDriveMailSenderService;
import TM.service.TestDriveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testDrive")
@RequiredArgsConstructor
public class TestDriveController {

  private final TestDriveService testDriveService;
  private final TestDriveMailSenderService mailSender;

  @GetMapping
  public String getTestDriveFormPage(Model model) {
    model.addAttribute("testDriveForm", new TestDriveForm());
    model.addAttribute("modelTypes", ModelType.values());
    model.addAttribute("engineTypes", EngineType.values());
    model.addAttribute("dealershipLocations", DealershipLocation.values());
    return "testDriveForm";
  }

  @PostMapping
  public String sendTestDriveForm(
      @ModelAttribute @Valid TestDriveForm testDriveForm,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("testDriveForm", testDriveForm);
      model.addAttribute("modelTypes", ModelType.values());
      model.addAttribute("engineTypes", EngineType.values());
      model.addAttribute("dealershipLocations", DealershipLocation.values());
      return "testDriveForm";
    }

    model.addAttribute("testDrive", testDriveService.mapFormToTestDrive(testDriveForm));
    model.addAttribute("selectedDate", testDriveForm.getDateTime());
    mailSender.sendEmailConfirmation(
        testDriveForm.getEmail(), testDriveService.mapFormToTestDrive(testDriveForm));
    return "testDriveConfirmation";
  }
}
