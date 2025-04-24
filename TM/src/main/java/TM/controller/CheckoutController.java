package TM.controller;

import TM.model.Order;
import TM.service.CartService;
import TM.service.OrderMailSenderService;
import TM.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

  private final CartService cartService;
  private final OrderService orderService;
  private final OrderMailSenderService mailSender;

  @GetMapping()
  public String getCheckoutPage(Authentication authentication, Model model) {
    model.addAttribute("order", new Order());
    model.addAttribute(
        "cartPrice",
        cartService.getTotalCartPrice(cartService.getCartByUser(authentication.getName())));
    model.addAttribute("shippingCost", 25);
    model.addAttribute(
        "totalPrice",
        cartService.getTotalCartPrice(cartService.getCartByUser(authentication.getName())) + 25);
    return "checkoutPage";
  }

  @PostMapping("/submitOrder")
  public String sendOrder(
      Authentication authentication,
      @ModelAttribute Order order,
      Model model) {

    Order savedOrder =
        orderService.sendOrder(
            authentication.getName(),
            order.getFirstName(),
            order.getLastName(),
            order.getEmail(),
            order.getPhoneNumber(),
            order.getDeliveryAddress());

    model.addAttribute("order", savedOrder);
    mailSender.sendEmailConfirmation(savedOrder.getEmail(), savedOrder);
    return "orderConfirmation";
  }
}
