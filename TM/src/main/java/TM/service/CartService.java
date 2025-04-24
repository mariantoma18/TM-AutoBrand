package TM.service;

import TM.model.Accessory;
import TM.model.Cart;
import TM.model.Item;
import TM.model.User;
import TM.repository.AccessoriesRepository;
import TM.repository.CartRepository;
import TM.repository.ItemRepository;
import TM.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final ItemRepository itemRepository;
  private final AccessoriesRepository accessoriesRepository;
  private final UserRepository userRepository;

  public void addItemsToCart(int cartId, int accessoryId, int quantity) {

    Cart cart =
        cartRepository
            .findById(cartId)
            .orElseGet(
                () -> {
                  Cart newCart = new Cart();
                  cartRepository.save(newCart);
                  return newCart;
                });

    Accessory accessory =
        accessoriesRepository
            .findById(accessoryId)
            .orElseThrow(() -> new RuntimeException("No accessory find with id: " + accessoryId));

    Optional<Item> existingItem = itemRepository.findByCartAndAccessory(cart, accessory);

    if (existingItem.isPresent()) {
      Item item = existingItem.get();
      item.setQuantity(item.getQuantity() + 1);

      itemRepository.save(item);
    } else {
      Item item = new Item(accessory, cart, 1);

      itemRepository.save(item);
      cart.getItems().add(item);
    }
  }

  public Cart getCart(int cartId) {
    return cartRepository
        .findById(cartId)
        .orElseThrow(() -> new RuntimeException("No cart found with id: " + cartId));
  }

  public Cart getCartByUser(String username) {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new RuntimeException("No user found with username: " + username));

    Cart cart = user.getCart();

    if (user.getCart() == null) {
      cart = new Cart(user);
      cart.setUser(user);
      user.setCart(cart);

      cartRepository.save(cart);
    }

    return cart;
  }

  public double getTotalCartPrice(Cart cart) {
    double totalPrice = 0;

    if (cart.getItems() == null) {
      return totalPrice;
    }

    for (Item item : cart.getItems()) {
      double itemTotalPrice = calculateItemPrice(item);
      totalPrice += itemTotalPrice;
    }

    return totalPrice;
  }

  public double calculateItemPrice(Item item) {
    if (item == null || item.getAccessory() == null) {
      return 0;
    }

    return item.getAccessory().getPrice() * item.getQuantity();
  }

  public void updateItemQuantity(int itemId, int quantity) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new RuntimeException("No item found with id: " + itemId));
    item.setQuantity(quantity);
    itemRepository.save(item);
  }

  @Transactional
  public void deleteItem(int itemId) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new RuntimeException("No item found with id: " + itemId));
    itemRepository.delete(item);

    Cart cart = item.getCart();
    cart.getItems().removeIf(it -> it.getId() == itemId);

    cartRepository.save(cart);
  }
}
