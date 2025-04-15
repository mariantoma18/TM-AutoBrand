package TM.service;

import TM.model.Enums.ConsumptionType;
import TM.model.Enums.EngineType;
import TM.model.Enums.ModelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumptionCalculatorService {

    private final ConsumptionService consumptionService;

    public Double getFuelConsumptionInLiters(Long monthlyKms, ModelType modelType, EngineType engineType, ConsumptionType consumptionType){

        Double consumption = consumptionService.getConsumptionForModelAndEngine(modelType,engineType, consumptionType);

        return (monthlyKms * consumption)/100;
    }
}
