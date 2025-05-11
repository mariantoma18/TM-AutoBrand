package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import TM.model.Accessory;
import TM.model.Cart;
import TM.model.Item;
import TM.model.User;
import TM.repository.AccessoriesRepository;
import TM.repository.CartRepository;
import TM.repository.ItemRepository;
import TM.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartServiceTest {

  @Mock private CartRepository cartRepository;

  @Mock private AccessoriesRepository accessoriesRepository;

  @Mock private ItemRepository itemRepository;

  @Mock private UserRepository userRepository;

  @InjectMocks private CartService cartService;

  @Test
  void addItemsToCartWhenItemDoesNotExist() {
    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());
    Accessory accessory = new Accessory();

    int cartId = 1;
    int accessoryId = 1;

    when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
    when(accessoriesRepository.findById(accessoryId)).thenReturn(Optional.of(accessory));
    when(itemRepository.findByCartAndAccessory(cart, accessory)).thenReturn(Optional.empty());

    cartService.addItemsToCart(cartId, accessoryId, 1);

    assertEquals(1, cart.getItems().size());
  }

  @Test
  void addItemsToCartWhenItemExist() {
    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());

    Accessory accessory = new Accessory();
    Item currentItem = new Item(accessory, cart, 1);

    int cartId = 1;
    int accessoryId = 1;

    cart.getItems().add(currentItem);

    when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
    when(accessoriesRepository.findById(accessoryId)).thenReturn(Optional.of(accessory));
    when(itemRepository.findByCartAndAccessory(cart, accessory)).thenReturn(Optional.empty());

    cartService.addItemsToCart(cartId, accessoryId, 1);

    assertEquals(2, cart.getItems().size());
    verify(itemRepository).save(currentItem);
  }

  @Test
  void getCartWhenNoData() {

    int cartId = 1;

    when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartService.getCart(cartId));

    assertEquals("No cart found with id: 1", exception.getMessage());
  }

  @Test
  void getCartWhenDataExists() {

    int cartId = 1;

    when(cartRepository.findById(cartId)).thenReturn(Optional.of(new Cart()));

    Cart cart = cartService.getCart(cartId);

    assertEquals(new Cart(), cart);
  }

  @Test
  void getCartByUserWhenNoData() {
    String username = "Username";
    User user = new User();
    Cart cart = new Cart();

    when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartService.getCartByUser(username));

    assertEquals("No user found with username: Username", exception.getMessage());
  }

  @Test
  void getCartByUserWhenDataExists() {
    String username = "Username";
    Cart cart = new Cart();
    User user = new User();
    user.setUsername(username);
    user.setCart(cart);

    when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

    Cart specificCart = cartService.getCartByUser(user.getUsername());

    assertEquals(cart, specificCart);
  }

  @Test
  void getTotalCartPriceWithNoItems() {
    double price = cartService.getTotalCartPrice(new Cart());

    assertEquals(0, price);
  }

  @Test
  void getTotalCartPriceWithItems() {
    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());

    Accessory accessory = new Accessory();
    accessory.setPrice(50.0);

    Item item = new Item(accessory, cart, 2);

    cart.getItems().add(item);

    double price = cartService.getTotalCartPrice(cart);

    assertEquals(100.0, price);
  }

  @Test
  void calculateItemPriceWhenNoItemExists() {
    Item item = new Item();

    double price = cartService.calculateItemPrice(item);

    assertEquals(0, price);
  }

  @Test
  void calculateItemPriceWhenExist() {
    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());

    Accessory accessory = new Accessory();
    accessory.setPrice(100.0);

    Item item = new Item(accessory, cart, 3);

    double price = cartService.calculateItemPrice(item);

    assertEquals(300, price);
  }

  @Test
  void updateItemQuantityWhenNoItemExists() {
    int itemId = 1;

    when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartService.updateItemQuantity(itemId, 10));

    assertEquals("No item found with id: 1", exception.getMessage());
  }

  @Test
  void updateItemQuantityWhenItemExists() {
    int itemId = 1;
    int quantity = 3;

    Item item = new Item();
    item.setQuantity(1);

    when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

    cartService.updateItemQuantity(itemId, quantity);

    assertEquals(3, item.getQuantity());
    verify(itemRepository).save(item);
  }

  @Test
  void deleteItemWhenNoData() {
    int itemId = 1;

    when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> cartService.deleteItem(itemId));

    assertEquals("No item found with id: 1", exception.getMessage());
  }

  @Test
  void deleteItemWhenExists() {
    int itemId = 1;

    Cart cart = new Cart();
    cart.setItems(new ArrayList<>());

    Item item = new Item();
    item.setId(itemId);
    item.setCart(cart);

    cart.getItems().add(item);

    when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

    cartService.deleteItem(itemId);

    assertTrue(cart.getItems().isEmpty());
    verify(itemRepository).delete(item);
    verify(cartRepository).save(cart);
  }
}
