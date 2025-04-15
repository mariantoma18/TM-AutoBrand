package TM.controller;

import TM.model.Contact;
import TM.model.Form.ContactForm;
import TM.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public String getContactForm(Model model){
        model.addAttribute("contactForm", new ContactForm());
        return "contactForm";
    }

    @PostMapping
    public String sendContactForm(@ModelAttribute ContactForm contactForm, Model model){
        model.addAttribute("contact", contactService.mapFormToContact(contactForm));
        return "contactRequestConfirmation";
    }
}
