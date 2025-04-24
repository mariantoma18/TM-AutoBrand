package TM.controller;

import TM.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  @GetMapping("/view")
  public String getCartPage(Authentication authentication, Model model) {
    model.addAttribute("cart", cartService.getCartByUser(authentication.getName()));
    model.addAttribute(
        "totalPrice",
        cartService.getTotalCartPrice(cartService.getCartByUser((authentication.getName()))));
    return "viewCartPage";
  }

  @PostMapping("/add")
  public String addItemToCart(
      Authentication authentication,
      @RequestParam int accessoryId,
      @RequestParam int quantity,
      Model model) {
    cartService.addItemsToCart(
        cartService.getCartByUser(authentication.getName()).getId(), accessoryId, quantity);
    model.addAttribute("message", "The item has been added to your cart!");
    return "redirect:/cart/view";
  }

  @PostMapping("/updateQuantity")
  public String updateItemQuantity(@RequestParam int itemId, @RequestParam int quantity){
    cartService.updateItemQuantity(itemId, quantity);
    return "redirect:/cart/view";
  }

  @PostMapping("/delete")
  public String deleteItem(@RequestParam int itemId){
    cartService.deleteItem(itemId);
    return "redirect:/cart/view";
  }
}
