package TM.service;

import TM.model.Accessory;
import TM.model.AccessoryCategory;
import TM.repository.AccessoriesRepository;
import TM.repository.AccessoryCategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessoriesService {

  private final AccessoriesRepository accessoriesRepository;
  private final AccessoryCategoryRepository accessoryCategoryRepository;

  public List<AccessoryCategory> getAllAccessoryCategories() {
    List<AccessoryCategory> categories = accessoryCategoryRepository.findAll();

    if (categories.isEmpty()) {
      throw new RuntimeException("No category found!");
    }

    return categories;
  }

  public List<Accessory> getCarCareAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(2);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public List<Accessory> getBrakingSystemAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(3);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public List<Accessory> getInteriorAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(5);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public List<Accessory> getExteriorAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(4);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public List<Accessory> getWinterAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(6);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public List<Accessory> getMotorOilAccessories() {
    List<Accessory> accessories = accessoriesRepository.findByAccessoryCategoryId(1);

    if (accessories.isEmpty()) {
      throw new RuntimeException("No accessories found!");
    }

    return accessories;
  }

  public Accessory getAccessoryById(int id) {
    return accessoriesRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("No accessory find with id: " + id));
  }
}
