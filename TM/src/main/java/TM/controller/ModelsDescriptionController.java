package TM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/models")
public class ModelsDescriptionController {

    @GetMapping()
    public String getModelsDescriptionPage(){
        return "modelsDescription";
    }
}
