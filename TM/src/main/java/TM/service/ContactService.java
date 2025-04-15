package TM.service;

import TM.model.Contact;
import TM.model.Form.ContactForm;
import TM.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact mapFormToContact(ContactForm contactForm){
        Contact contact = new Contact(
                contactForm.getFirstName(),
                contactForm.getLastName(),
                contactForm.getEmail(),
                contactForm.getPhoneNumber(),
                contactForm.getEmailSubject(),
                contactForm.getEmailContent());

        contactRepository.save(contact);

        return contact;
    }
}
