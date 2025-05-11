package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import TM.model.Contact;
import TM.model.Form.ContactForm;
import TM.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactServiceTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private ContactService contactService;

  @Test
  void mapFormToContact() {
    ContactForm contactForm = new ContactForm();
    contactForm.setFirstName("Test");
    contactForm.setLastName("Test");
    contactForm.setEmail("test@gmail.com");
    contactForm.setPhoneNumber("0733688333");
    contactForm.setEmailSubject("Subject");
    contactForm.setEmailContent("Content here...");

    Contact contact =
        new Contact(
            contactForm.getFirstName(),
            contactForm.getLastName(),
            contactForm.getEmail(),
            contactForm.getPhoneNumber(),
            contactForm.getEmailSubject(),
            contactForm.getEmailContent());

    when(contactRepository.save(contact)).thenReturn(contact);

    Contact resultContact = contactService.mapFormToContact(contactForm);

    assertEquals("test@gmail.com", resultContact.getEmail());
    verify(contactRepository).save(contact);
  }
}
