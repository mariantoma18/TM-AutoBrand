package TM.controller;

import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.model.Form.TestDriveForm;
import TM.model.TestDrive;
import TM.service.TestDriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testDrive")
@RequiredArgsConstructor
public class TestDriveController {

    private final TestDriveService testDriveService;

    @GetMapping
    public String getTestDriveFormPage(Model model){
        model.addAttribute("testDriveForm", new TestDriveForm());
        model.addAttribute("modelTypes", ModelType.values());
        model.addAttribute("engineTypes", EngineType.values());
        return "testDriveForm";
    }

    @PostMapping
    public String sendTestDriveForm(@ModelAttribute TestDriveForm testDriveForm, Model model){
        TestDrive testDrive = testDriveService.mapFormToTestDrive(testDriveForm);
        model.addAttribute("testDrive", testDrive);
        model.addAttribute("selectedDate", testDriveForm.getDateTime());
        return "testDriveConfirmation";
    }
}
