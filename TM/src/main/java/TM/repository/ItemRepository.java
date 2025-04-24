package TM.repository;

import TM.model.Accessory;
import TM.model.Cart;
import TM.model.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
  List<Item> findByCartId(int id);

  Optional<Item> findByCartAndAccessory(Cart cart, Accessory accessory);
}
