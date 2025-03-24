package TM.controller;

import TM.model.CarConfiguratorForm;
import TM.model.Sedan;
import TM.service.ConfiguratorService;
import TM.service.SedanConfiguratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/configurator")
@RequiredArgsConstructor
public class ConfiguratorController {

    private final ConfiguratorService configuratorService;
    private final SedanConfiguratorService sedanConfiguratorService;

    @GetMapping
    public String getConfiguratorPage(){
        return "configuratorPage";
    }

    @GetMapping("/sedan")
    public String getSedanConfiguratorPage(Model model){
        model.addAttribute("carConfiguratorForm", new CarConfiguratorForm());
        model.addAttribute("availableExteriorOptions", configuratorService.getAvailableExteriorColors());
        model.addAttribute("availableInteriorOptions", configuratorService.getAvailableInteriorColors());
        model.addAttribute("availableEngineTypes", configuratorService.getAvailableEngineTypes());
        return "sedanConfigurator";
    }

    @PostMapping("/sedan")
    public String receiveSedanConfiguration(@ModelAttribute CarConfiguratorForm configuratorForm, Model model){
        Sedan configuredSedan = sedanConfiguratorService.createSedan(configuratorForm);
        model.addAttribute("configuredSedan", configuredSedan);
        return "sedanView";
    }

}
