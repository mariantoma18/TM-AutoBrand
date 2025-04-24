package TM.controller;

import TM.service.AccessoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/accessories")
@RequiredArgsConstructor
public class AccessoriesController {

    private final AccessoriesService accessoriesService;

    @GetMapping()
    public String getAccessoriesPage(Model model){
        model.addAttribute("categories", accessoriesService.getAllAccessoryCategories());
        return "accessoriesPage";
    }

    @GetMapping("/carCare")
    public String getCarCareAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getCarCareAccessories());
        return "accessoriesPageCarCare";
    }

    @GetMapping("/brakingSystem")
    public String getBrakingSystemAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getBrakingSystemAccessories());
        return "accessoriesPageBrakingSystem";
    }

    @GetMapping("/interior")
    public String getInteriorAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getInteriorAccessories());
        return "accessoriesPageInterior";
    }

    @GetMapping("/exterior")
    public String getExteriorAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getExteriorAccessories());
        return "accessoriesPageExterior";
    }

    @GetMapping("/winter")
    public String getWinterAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getWinterAccessories());
        return "accessoriesPageWinter";
    }

    @GetMapping("/motorOil")
    public String getMotorOilAccessories(Model model){
        model.addAttribute("accessories", accessoriesService.getMotorOilAccessories());
        return "accessoriesPageMotorOil";
    }

    @GetMapping("/details")
    public String getAccessoryDetailsPage(@RequestParam int accessoryId, Model model){
        model.addAttribute("accessory", accessoriesService.getAccessoryById(accessoryId));
        return "accessoryDetails";
    }
}
