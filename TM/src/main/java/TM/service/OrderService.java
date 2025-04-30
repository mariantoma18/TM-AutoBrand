package TM.service;

import TM.model.*;
import TM.repository.CartRepository;
import TM.repository.ItemRepository;
import TM.repository.OrderRepository;
import TM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final ItemRepository itemRepository;

  private final CartService cartService;

  @Transactional
  public Order sendOrder(
      String username,
      String firstName,
      String lastName,
      String email,
      String phoneNumber,
      String deliveryAddress) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new RuntimeException("No user found with username: " + username));

    Cart cart =
        cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("No cart found."));

    List<OrderItem> orderItems = mapCartItemsToOrderItems(cart);

    double cartPrice = cartService.getTotalCartPrice(cart);

    Order order =
        new Order(user, firstName, lastName, email, phoneNumber, deliveryAddress, cartPrice, 25);

    orderRepository.save(order);

    for (OrderItem orderItem : orderItems) {
      orderItem.setOrder(order);
      order.getOrderItems().add(orderItem);
    }

    itemRepository.deleteAll(cart.getItems());
    cart.getItems().clear();
    cartRepository.save(cart);

    return order;
  }

  public List<OrderItem> mapCartItemsToOrderItems(Cart cart) {
    List<OrderItem> orderItems = new ArrayList<>();

    for (Item cartItem : cart.getItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setName(cartItem.getAccessory().getName());
      orderItem.setQuantity(cartItem.getQuantity());
      orderItem.setPrice(cartItem.getAccessory().getPrice());
      orderItems.add(orderItem);
    }
    return orderItems;
  }
}
