package TM.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import TM.model.Accessory;
import TM.model.AccessoryCategory;
import TM.repository.AccessoriesRepository;
import TM.repository.AccessoryCategoryRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccessoriesServiceTest {

  @Mock private AccessoriesRepository accessoriesRepository;

  @Mock private AccessoryCategoryRepository accessoryCategoryRepository;

  @InjectMocks private AccessoriesService accessoriesService;

  @Test
  public void getAllAccessoryCategoriesWhenNoDataExpectException() {

    when(accessoryCategoryRepository.findAll()).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getAllAccessoryCategories());
    assertEquals("No category found!", exception.getMessage());
  }

  @Test
  public void getAllAccessoryCategoriesWhenDataExpectCategoryList() {
    AccessoryCategory accessoryCategory1 = new AccessoryCategory();
    AccessoryCategory accessoryCategory2 = new AccessoryCategory();

    List<AccessoryCategory> accessoryCategoryList =
        Arrays.asList(accessoryCategory1, accessoryCategory2);

    when(accessoryCategoryRepository.findAll()).thenReturn(accessoryCategoryList);

    List<AccessoryCategory> categories = accessoriesService.getAllAccessoryCategories();

    assertEquals(2, categories.size());
  }

  @Test
  void getCarCareAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(2)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getCarCareAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getCarCareAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();
    Accessory accessory2 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

    when(accessoriesRepository.findByAccessoryCategoryId(2)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getCarCareAccessories();

    assertEquals(2, accessoriesList.size());
  }

  @Test
  void getBrakingSystemAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(3)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> accessoriesService.getBrakingSystemAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getBrakingSystemAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();
    Accessory accessory2 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

    when(accessoriesRepository.findByAccessoryCategoryId(3)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getBrakingSystemAccessories();

    assertEquals(2, accessoriesList.size());
  }

  @Test
  void getInteriorAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(5)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getInteriorAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getInteriorAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();
    Accessory accessory2 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

    when(accessoriesRepository.findByAccessoryCategoryId(5)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getInteriorAccessories();

    assertEquals(2, accessoriesList.size());
  }

  @Test
  void getExteriorAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(4)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getExteriorAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getExteriorAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();
    Accessory accessory2 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

    when(accessoriesRepository.findByAccessoryCategoryId(4)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getExteriorAccessories();

    assertEquals(2, accessoriesList.size());
  }

  @Test
  void getWinterAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(6)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getWinterAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getWinterAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();
    Accessory accessory2 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

    when(accessoriesRepository.findByAccessoryCategoryId(6)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getWinterAccessories();

    assertEquals(2, accessoriesList.size());
  }

  @Test
  void getMotorOilAccessoriesWhenNoDataExpectException() {
    when(accessoriesRepository.findByAccessoryCategoryId(1)).thenReturn(Collections.emptyList());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getMotorOilAccessories());

    assertEquals("No accessories found!", exception.getMessage());
  }

  @Test
  void getMotorOilAccessoriesWhenDataExpectList() {
    Accessory accessory1 = new Accessory();

    List<Accessory> accessories = Arrays.asList(accessory1);

    when(accessoriesRepository.findByAccessoryCategoryId(1)).thenReturn(accessories);

    List<Accessory> accessoriesList = accessoriesService.getMotorOilAccessories();

    assertEquals(1, accessoriesList.size());
  }

  @Test
  void getAccessoryByIdWhenNoDataExpectException() {
    when(accessoriesRepository.findById(1)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(RuntimeException.class, () -> accessoriesService.getAccessoryById(1));

    assertEquals("No accessory found with ID: 1", exception.getMessage());
  }

  @Test
  void getAccessoryByIdWhenDataExpectAccessory() {
    Accessory accessory = new Accessory();
    accessory.setId(1);

    when(accessoriesRepository.findById(1)).thenReturn(Optional.of(accessory));

    Accessory accessoryResult = accessoriesService.getAccessoryById(1);

    assertEquals(1, accessoryResult.getId());
  }
}
