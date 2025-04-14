package TM.controller;

import TM.model.*;
import TM.model.Form.CarConfiguratorForm;
import TM.model.Form.OfferRequestForm;
import TM.service.*;
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
    private final MailSenderService mailSenderService;

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
        model.addAttribute("configuredSedanDto", sedanConfiguratorService.createSedan(configuratorForm));
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
        OfferRequest offer = offerRequestService.mapSedanToOfferRequest(offerRequestForm, sedanConfiguratorService.getSedanById(id));
        model.addAttribute("offerRequest" , offerRequestService.mapSedanToOfferRequest(offerRequestForm, sedanConfiguratorService.getSedanById(id)));
        mailSenderService.sendEmailConfirmation(offer.getEmail(), offer.getSedan().getExteriorColor().name(), sedanConfiguratorService.getSedanById(id));
        return "sedanOfferConfirmation";
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
        model.addAttribute("configuredSuvDto", suvConfiguratorService.createSuv(configuratorForm));
        return "suvView";
    }

    @GetMapping("/suvOfferRequest")
    public String getSuvOfferRequestPage(Model model, @RequestParam(required = true) Integer id){
        model.addAttribute("offerRequestForm", new OfferRequestForm());
        model.addAttribute("id", id);
        return "suvOfferRequestForm";
    }

    @PostMapping("/suvOfferRequest")
    public String sendSuvOfferRequest(@RequestParam int id, @ModelAttribute OfferRequestForm offerRequestForm, Model model){
        OfferRequest offer = offerRequestService.mapSuvToOfferRequest(offerRequestForm, suvConfiguratorService.getSuvById(id));
        model.addAttribute("offerRequest" , offer);
        mailSenderService.sendEmailConfirmation(offer.getEmail(), offer.getSuv().getExteriorColor().name(), suvConfiguratorService.getSuvById(id));
        return "suvOfferConfirmation";
    }

}
