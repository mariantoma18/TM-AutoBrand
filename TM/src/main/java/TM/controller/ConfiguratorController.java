package TM.controller;

import TM.model.CarConfiguratorForm;
import TM.model.Dto.SedanDto;
import TM.model.Dto.SuvDto;
import TM.model.OfferRequest;
import TM.model.OfferRequestForm;
import TM.model.Sedan;
import TM.service.ConfiguratorService;
import TM.service.OfferRequestService;
import TM.service.SedanConfiguratorService;
import TM.service.SuvConfiguratorService;
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
    private final SuvConfiguratorService suvConfiguratorService;
    private final OfferRequestService offerRequestService;

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
        SedanDto configuredSedanDto = sedanConfiguratorService.createSedan(configuratorForm);
        model.addAttribute("configuredSedanDto", configuredSedanDto);
        return "sedanView";
    }

    @GetMapping("/sedanOfferRequest")
    public String getSedanOfferRequestPage(Model model, @RequestParam(required = true) Integer id){
        model.addAttribute("offerRequestForm", new OfferRequestForm());
        model.addAttribute("id", id);
        return "sedanOfferRequestForm";
    }

    @PostMapping("/sedanOfferRequest")
    public String sendSedanOfferRequest(@RequestParam int id, @ModelAttribute OfferRequestForm offerRequestForm, Model model){
        Sedan sedan = sedanConfiguratorService.getSedanById(id);
        OfferRequest offer = offerRequestService.mapToOfferRequest(offerRequestForm, sedan);
        model.addAttribute("offerRequest" , offer);
        return "offerConfirmation";
    }

    @GetMapping("/suv")
    public String getSuvConfiguratorPage(Model model){
        model.addAttribute("carConfiguratorForm", new CarConfiguratorForm());
        model.addAttribute("availableExteriorOptions", configuratorService.getAvailableExteriorColors());
        model.addAttribute("availableInteriorOptions", configuratorService.getAvailableInteriorColors());
        model.addAttribute("availableEngineTypes", configuratorService.getAvailableEngineTypes());
        return "suvConfigurator";
    }

    @PostMapping("/suv")
    public String receiveSuvConfiguration(@ModelAttribute CarConfiguratorForm configuratorForm, Model model){
        SuvDto configuredSuvDto = suvConfiguratorService.createSuv(configuratorForm);
        model.addAttribute("configuredSuvDto", configuredSuvDto);
        return "suvView";
    }

}
