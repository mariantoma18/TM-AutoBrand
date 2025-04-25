package TM.controller;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import TM.model.Form.ConsumptionForm;
import TM.service.ConsumptionCalculatorService;
import TM.service.ConsumptionService;
import TM.service.FuelPriceService;
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
@RequestMapping("/consumption")
@RequiredArgsConstructor
public class ConsumptionCalculatorController {

  private final ConsumptionCalculatorService calculatorService;
  private final ConsumptionService consumptionService;
  private final FuelPriceService fuelPriceService;

  @GetMapping
  public String getConsumptionCalculatorPage(Model model) {
    model.addAttribute("consumptionForm", new ConsumptionForm());
    model.addAttribute("modelTypes", ModelType.values());
    model.addAttribute("engineTypes", EngineType.values());
    model.addAttribute("consumptionTypes", ConsumptionType.values());
    model.addAttribute("consumptionList", consumptionService.getAllConsumptionsDetails());
    return "consumptionCalculatorPage";
  }

  @PostMapping
  public String getConsumptionResult(
      @ModelAttribute @Valid ConsumptionForm consumptionForm,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()){
      model.addAttribute("consumptionForm", consumptionForm);
      model.addAttribute("modelTypes", ModelType.values());
      model.addAttribute("engineTypes", EngineType.values());
      model.addAttribute("consumptionTypes", ConsumptionType.values());
      model.addAttribute("consumptionList", consumptionService.getAllConsumptionsDetails());
      return "consumptionCalculatorPage";
    }

    model.addAttribute(
        "fuelConsumption",
        calculatorService.calculateFuelConsumptionInLiters(
            consumptionForm.getMonthlyKms(),
            consumptionForm.getModelType(),
            consumptionForm.getEngineType(),
            consumptionForm.getConsumptionType()));
    model.addAttribute(
        "fuelPrice",
        calculatorService.calculateFuelCost(
            consumptionForm.getMonthlyKms(),
            consumptionForm.getModelType(),
            consumptionForm.getEngineType(),
            consumptionForm.getConsumptionType()));
    model.addAttribute("engineType", consumptionForm.getEngineType());
    model.addAttribute("fuelPrices", fuelPriceService.getFuelPrice());
    return "consumptionResult";
  }
}
