package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import TM.model.*;
import TM.repository.CartRepository;
import TM.repository.ItemRepository;
import TM.repository.OrderRepository;
import TM.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

  @Mock private OrderRepository orderRepository;

  @Mock private UserRepository userRepository;

  @Mock private CartRepository cartRepository;

  @Mock private ItemRepository itemRepository;

  @Mock private CartService cartService;

  @InjectMocks private OrderService orderService;

  @Test
  void sendOrderWhenDataExists() {
    User user = new User();
    user.setUsername("Test");

    Accessory accessory = new Accessory();
    accessory.setName("My Accessory");
    accessory.setPrice(20.0);

    Item item = new Item();
    item.setAccessory(accessory);
    item.setQuantity(2);

    Cart cart = new Cart();
    cart.setItems(new ArrayList<>(List.of(item)));
    cart.setUser(user);

    when(userRepository.findByUsername("Test")).thenReturn(Optional.of(user));
    when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
    when(cartService.getTotalCartPrice(cart)).thenReturn(40.0);
    when(orderRepository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));

    Order order =
        orderService.sendOrder(
            user.getUsername(),
            "MyFirstName",
            "MyLastName",
            "test@gmail.com",
            "0711111111",
            "Str. Principala");

    assertEquals("MyFirstName", order.getFirstName());
  }

  @Test
  void sendOrderWhenNoUserData() {
    when(userRepository.findByUsername("Test")).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                orderService.sendOrder(
                    "Test",
                    "MyFirstName",
                    "MyLastName",
                    "test@gmail.com",
                    "0700000000",
                    "Address"));

    assertEquals("No user found with username: Test", exception.getMessage());
  }

  @Test
  void sendOrderWhenNoCartData() {
    User user = new User();
    user.setUsername("Test");

    when(userRepository.findByUsername("Test")).thenReturn(Optional.of(user));
    when(cartRepository.findByUser(user)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                orderService.sendOrder(
                    "Test",
                    "MyFirstName",
                    "MyLastName",
                    "test@gmail.com",
                    "0700000000",
                    "Address"));

    assertEquals("No cart found.", exception.getMessage());
  }

  @Test
  void mapCartItemsToOrderItems() {
    Accessory accessory = new Accessory();
    accessory.setName("MyAccessory");
    accessory.setPrice(30.0);

    Item item = new Item();
    item.setAccessory(accessory);
    item.setQuantity(2);

    Cart cart = new Cart();
    cart.setItems(List.of(item));

    List<OrderItem> result = orderService.mapCartItemsToOrderItems(cart);

    assertEquals(1, result.size());
  }

  @Test
  void mapCartItemsToOrderItemsWhenCartIsEmpty() {
    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());

    List<OrderItem> result = orderService.mapCartItemsToOrderItems(cart);

    assertTrue(result.isEmpty());
  }
}
