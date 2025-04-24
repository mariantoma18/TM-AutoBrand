package TM.service;

import TM.model.TestDrive;
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
public class TestDriveMailSenderService {

  private final JavaMailSender mailSender;

  public void sendEmailConfirmation(String clientEmail, TestDrive testDrive) {
    String htmlEmailTemplate;
    String finalHtmlEmailTemplate;
    String mapLink = "";

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setFrom("marian.toma2818@gmail.com");
      helper.setTo(clientEmail);
      helper.setSubject("Test-Drive Request Confirmation");

      try (InputStream inputStream =
          getClass().getResourceAsStream("/templates/emailConfirmationTestDrive.html")) {
        htmlEmailTemplate = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      if (testDrive.getDealershipLocation().name().equals("BRASOV")) {
        mapLink =
            "https://www.google.com/maps/place/45%C2%B040'13.7%22N+25%C2%B036'35.7%22E/@45.670476,25.60901,304m/data=!3m1!1e3!4m4!3m3!8m2!3d45.6704722!4d25.6099167?hl=en&entry=ttu&g_ep=EgoyMDI1MDQwOS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D";
      } else if (testDrive.getDealershipLocation().name().equals("BUCHAREST")) {
        mapLink =
            "https://www.google.com/maps/place/44%C2%B029'45.6%22N+26%C2%B000'22.4%22E/@44.495985,26.004922,620m/data=!3m1!1e3!4m4!3m3!8m2!3d44.496!4d26.0062222?hl=en&entry=ttu&g_ep=EgoyMDI1MDQwOS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D";
      }

      finalHtmlEmailTemplate =
          htmlEmailTemplate
              .replace("${firstName}", testDrive.getFirstName())
              .replace("${lastName}", testDrive.getLastName())
              .replace("${formattedDateTime}", testDrive.getDateTime().toString())
              .replace("${modelType}", testDrive.getModelType().getDescription())
              .replace("${engineType}", testDrive.getEngineType().getDescription())
              .replace("${location}", testDrive.getDealershipLocation().getDescription())
              .replace("${mapLink}", mapLink);

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
}
