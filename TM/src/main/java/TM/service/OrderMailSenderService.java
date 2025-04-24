package TM.service;

import TM.model.Order;
import TM.model.TestDrive;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class OrderMailSenderService {

    private final JavaMailSender mailSender;

    public void sendEmailConfirmation(String clientEmail, Order order) {
        String htmlEmailTemplate;
        String finalHtmlEmailTemplate;
        String mapLink = "";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("marian.toma2818@gmail.com");
            helper.setTo(clientEmail);
            helper.setSubject("Order Confirmation");

            try (InputStream inputStream =
                         getClass().getResourceAsStream("/templates/emailConfirmationOrder.html")) {
                htmlEmailTemplate = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            finalHtmlEmailTemplate =
                    htmlEmailTemplate
                            .replace("${order.firstName}", order.getFirstName())
                            .replace("${order.lastName}", order.getLastName())
                            .replace("${order.email}", order.getEmail())
                            .replace("${order.phoneNumber}", order.getPhoneNumber())
                            .replace("${order.orderTotalPrice}", String.valueOf(order.getOrderTotalPrice()))
                            .replace("${order.cartPrice}", String.valueOf(order.getCartPrice()))
                            .replace("${order.shippingCost}", String.valueOf(order.getShippingCost()))
                            .replace("${order.deliveryAddress}", order.getDeliveryAddress());

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
