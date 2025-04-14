package TM.service;

import TM.model.Sedan;
import TM.model.Suv;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfiguratorMailSenderService {

  private final JavaMailSender mailSender;

  public void sendEmailConfirmation(String clientEmail, String exteriorColor, Object car) {
    String htmlEmailTemplate;
    String finalHtmlEmailTemplate;
    String spin = "";

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom("marian.toma2818@gmail.com");
      helper.setTo(clientEmail);
      helper.setSubject("Offer Request Confirmation");

      try (InputStream inputStream =
          getClass().getResourceAsStream("/templates/emailConfirmationConfigurator.html")) {
        htmlEmailTemplate = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      if (car instanceof Sedan) {
        spin = getSedanSpinByColour(exteriorColor);
      } else {
        if (car instanceof Suv) {
          spin = getSuvSpinByColour(exteriorColor);
        }
      }

      finalHtmlEmailTemplate = htmlEmailTemplate.replace("${spin}", spin);

      helper.setText(finalHtmlEmailTemplate, true);

      helper.addInline(
          "TM-logo-black.png",
          new File(
              "C:\\Users\\Marian\\Desktop\\SI\\TM\\TM\\src\\main\\resources\\static\\images\\TM-logo-black.png"));

      mailSender.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  public String getSedanSpinByColour(String exteriorColor) {
    return switch (exteriorColor) {
      case "BLACK" -> "https://mariantoma.sirv.com/Elyssion_sedan/black_sedan/black_sedan.spin";
      case "BLUE" -> "https://mariantoma.sirv.com/Elyssion_sedan/blue_sedan/blue_sedan.spin";
      case "GREEN" -> "https://mariantoma.sirv.com/Elyssion_sedan/green_sedan/green_sedan.spin";
      case "RED" -> "https://mariantoma.sirv.com/Elyssion_sedan/red_sedan/red_sedan.spin";
      case "SILVER" -> "https://mariantoma.sirv.com/Elyssion_sedan/silver_sedan/silver_sedan.spin";
      default -> "No spin found.";
    };
  }

  public String getSuvSpinByColour(String exteriorColor) {
    return switch (exteriorColor) {
      case "BLACK" -> "https://mariantoma.sirv.com/Teraon_SUV/black_SUV/black_SUV.spin";
      case "BLUE" -> "https://mariantoma.sirv.com/Teraon_SUV/blue_SUV/blue_SUV.spin";
      case "GREEN" -> "https://mariantoma.sirv.com/Teraon_SUV/green_SUV/green_SUV.spin";
      case "RED" -> "https://mariantoma.sirv.com/Teraon_SUV/red_SUV/red_SUV.spin";
      case "SILVER" -> "https://mariantoma.sirv.com/Teraon_SUV/silver_SUV/silver_SUV.spin";
      default -> "No spin found.";
    };
  }
}
