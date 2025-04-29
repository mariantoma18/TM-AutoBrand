package TM.service;

import TM.model.Contact;
import TM.model.Order;
import TM.model.ServiceRequest;
import TM.model.TestDrive;
import TM.repository.ContactRepository;
import TM.repository.OrderRepository;
import TM.repository.ServiceRequestRepository;
import TM.repository.TestDriveRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final OrderRepository orderRepository;
  private final ServiceRequestRepository serviceRequestRepository;
  private final TestDriveRepository testDriveRepository;
  private final ContactRepository contactRepository;

  public List<Order> getActiveOrders() {
    return orderRepository.findByIsDoneFalse();
  }

  public List<Order> getDoneOrders() {
    return orderRepository.findByIsDoneTrue();
  }

  public void markAsDone(int orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("No order found with id: " + orderId));

    order.setDone(true);
    orderRepository.save(order);
  }

  public void markAsUndone(int orderId) {
    Order order =
            orderRepository
                    .findById(orderId)
                    .orElseThrow(() -> new RuntimeException("No order found with id: " + orderId));

    order.setDone(false);
    orderRepository.save(order);
  }

  public List<TestDrive> getAllTestDriveRequests() {
    return testDriveRepository.findAll();
  }

  public List<ServiceRequest> getAllServiceRequests() {
    return serviceRequestRepository.findAll();
  }

  public List<Contact> getAllContactRequests() {
    return contactRepository.findAll();
  }
}
