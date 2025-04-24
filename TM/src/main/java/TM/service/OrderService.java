package TM.service;

import TM.model.Cart;
import TM.model.Order;
import TM.model.User;
import TM.repository.CartRepository;
import TM.repository.ItemRepository;
import TM.repository.OrderRepository;
import TM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final ItemRepository itemRepository;

  private final CartService cartService;

  @Transactional
  public Order sendOrder(String username, String firstName, String lastName, String email, String phoneNumber, String deliveryAddress) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new RuntimeException("No user found with username: " + username));

    Cart cart = cartRepository.findByUser(user).orElseThrow(()-> new RuntimeException("No cart found."));

    double cartPrice = cartService.getTotalCartPrice(cart);

    Order order = new Order(user, firstName, lastName, email, phoneNumber, deliveryAddress, cartPrice, 25);

    orderRepository.save(order);

    itemRepository.deleteAll(cart.getItems());
    cart.getItems().clear();
    cartRepository.save(cart);

    return order;
  }
}
