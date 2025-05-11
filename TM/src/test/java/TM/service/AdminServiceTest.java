package TM.service;

import TM.model.Contact;
import TM.model.Order;
import TM.model.ServiceRequest;
import TM.model.TestDrive;
import TM.repository.ContactRepository;
import TM.repository.OrderRepository;
import TM.repository.ServiceRequestRepository;
import TM.repository.TestDriveRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdminServiceTest {

  @Mock private OrderRepository orderRepository;

  @Mock private ServiceRequestRepository serviceRequestRepository;

  @Mock private TestDriveRepository testDriveRepository;

  @Mock private ContactRepository contactRepository;

  @InjectMocks private AdminService adminService;

  @Test
  void getActiveOrdersWhenNoDataExpectSize0() {
    when(orderRepository.findByIsDoneFalse()).thenReturn(Collections.emptyList());
    List<Order> result = adminService.getActiveOrders();
    assertEquals(0, result.size());
  }

  @Test
  void getActiveOrdersWhenDataExpectSize3() {
    when(orderRepository.findByIsDoneFalse())
        .thenReturn(List.of(new Order(), new Order(), new Order()));
    List<Order> result = adminService.getActiveOrders();
    assertEquals(3, result.size());
  }

  @Test
  void getDoneOrdersWhenNoDataExpectSize0() {
    when(orderRepository.findByIsDoneTrue()).thenReturn(Collections.emptyList());
    List<Order> result = adminService.getDoneOrders();
    assertEquals(0, result.size());
  }

  @Test
  void getDoneOrdersWhenDataExpectSize3() {
    when(orderRepository.findByIsDoneTrue())
        .thenReturn(List.of(new Order(), new Order(), new Order()));
    List<Order> result = adminService.getDoneOrders();
    assertEquals(3, result.size());
  }

  @Test
  void getOrderByIdWhenNoDataExpectException() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.getOrderById(1));
  }

  @Test
  void getOrderByIdWhenDataExpectOrderReturned() {
    Order order = new Order();
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Order result = adminService.getOrderById(1);
    assertEquals(order, result);
  }

  @Test
  void markOrderAsDoneWhenNoDataExpectException() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markOrderAsDone(1));
  }

  @Test
  void markOrderAsDoneWhenDataExpectDoneTrue() {
    Order order = new Order();
    order.setDone(false);
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    adminService.markOrderAsDone(1);
    assertEquals(true, order.isDone());
  }

  @Test
  void markOrderAsUndoneWhenNoDataExpectException() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markOrderAsUndone(1));
  }

  @Test
  void markOrderAsUndoneWhenDataExpectDoneFalse() {
    Order order = new Order();
    order.setDone(true);
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    adminService.markOrderAsUndone(1);
    assertEquals(false, order.isDone());
  }

  // ServiceRequest tests
  @Test
  void getActiveServiceRequestsWhenNoDataExpectSize0() {
    when(serviceRequestRepository.findByIsDoneFalse()).thenReturn(Collections.emptyList());
    List<ServiceRequest> result = adminService.getActiveServiceRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getActiveServiceRequestsWhenDataExpectSize3() {
    when(serviceRequestRepository.findByIsDoneFalse())
        .thenReturn(List.of(new ServiceRequest(), new ServiceRequest(), new ServiceRequest()));
    List<ServiceRequest> result = adminService.getActiveServiceRequests();
    assertEquals(3, result.size());
  }

  @Test
  void getDoneServiceRequestsWhenNoDataExpectSize0() {
    when(serviceRequestRepository.findByIsDoneTrue()).thenReturn(Collections.emptyList());
    List<ServiceRequest> result = adminService.getDoneServiceRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getDoneServiceRequestsWhenDataExpectSize3() {
    when(serviceRequestRepository.findByIsDoneTrue())
        .thenReturn(List.of(new ServiceRequest(), new ServiceRequest(), new ServiceRequest()));
    List<ServiceRequest> result = adminService.getDoneServiceRequests();
    assertEquals(3, result.size());
  }

  @Test
  void markServiceRequestAsDoneWhenNoDataExpectException() {
    when(serviceRequestRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markServiceRequestAsDone(1));
  }

  @Test
  void markServiceRequestAsDoneWhenDataExpectDoneTrue() {
    ServiceRequest request = new ServiceRequest();
    request.setDone(false);
    when(serviceRequestRepository.findById(anyInt())).thenReturn(Optional.of(request));
    adminService.markServiceRequestAsDone(1);
    assertEquals(true, request.isDone());
  }

  @Test
  void markServiceRequestAsUndoneWhenNoDataExpectException() {
    when(serviceRequestRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markServiceRequestAsUndone(1));
  }

  @Test
  void markServiceRequestAsUndoneWhenDataExpectDoneFalse() {
    ServiceRequest request = new ServiceRequest();
    request.setDone(true);
    when(serviceRequestRepository.findById(anyInt())).thenReturn(Optional.of(request));
    adminService.markServiceRequestAsUndone(1);
    assertEquals(false, request.isDone());
  }

  // TestDrive tests
  @Test
  void getActiveTestDriveRequestsWhenNoDataExpectSize0() {
    when(testDriveRepository.findByIsDoneFalse()).thenReturn(Collections.emptyList());
    List<TestDrive> result = adminService.getActiveTestDriveRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getActiveTestDriveRequestsWhenDataExpectSize3() {
    when(testDriveRepository.findByIsDoneFalse())
        .thenReturn(List.of(new TestDrive(), new TestDrive(), new TestDrive()));
    List<TestDrive> result = adminService.getActiveTestDriveRequests();
    assertEquals(3, result.size());
  }

  @Test
  void getDoneTestDriveRequestsWhenNoDataExpectSize0() {
    when(testDriveRepository.findByIsDoneTrue()).thenReturn(Collections.emptyList());
    List<TestDrive> result = adminService.getDoneTestDriveRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getDoneTestDriveRequestsWhenDataExpectSize3() {
    when(testDriveRepository.findByIsDoneTrue())
        .thenReturn(List.of(new TestDrive(), new TestDrive(), new TestDrive()));
    List<TestDrive> result = adminService.getDoneTestDriveRequests();
    assertEquals(3, result.size());
  }

  @Test
  void markTestDriveRequestAsDoneWhenNoDataExpectException() {
    when(testDriveRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markTestDriveRequestAsDone(1));
  }

  @Test
  void markTestDriveRequestAsDoneWhenDataExpectDoneTrue() {
    TestDrive drive = new TestDrive();
    drive.setDone(false);
    when(testDriveRepository.findById(anyInt())).thenReturn(Optional.of(drive));
    adminService.markTestDriveRequestAsDone(1);
    assertEquals(true, drive.isDone());
  }

  @Test
  void markTestDriveRequestAsUndoneWhenNoDataExpectException() {
    when(testDriveRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markTestDriveRequestAsUndone(1));
  }

  @Test
  void markTestDriveRequestAsUndoneWhenDataExpectDoneFalse() {
    TestDrive drive = new TestDrive();
    drive.setDone(true);
    when(testDriveRepository.findById(anyInt())).thenReturn(Optional.of(drive));
    adminService.markTestDriveRequestAsUndone(1);
    assertEquals(false, drive.isDone());
  }

  // Contact tests
  @Test
  void getActiveContactRequestsWhenNoDataExpectSize0() {
    when(contactRepository.findByIsDoneFalse()).thenReturn(Collections.emptyList());
    List<Contact> result = adminService.getActiveContactRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getActiveContactRequestsWhenDataExpectSize3() {
    when(contactRepository.findByIsDoneFalse())
        .thenReturn(List.of(new Contact(), new Contact(), new Contact()));
    List<Contact> result = adminService.getActiveContactRequests();
    assertEquals(3, result.size());
  }

  @Test
  void getDoneContactRequestsWhenNoDataExpectSize0() {
    when(contactRepository.findByIsDoneTrue()).thenReturn(Collections.emptyList());
    List<Contact> result = adminService.getDoneContactRequests();
    assertEquals(0, result.size());
  }

  @Test
  void getDoneContactRequestsWhenDataExpectSize3() {
    when(contactRepository.findByIsDoneTrue())
        .thenReturn(List.of(new Contact(), new Contact(), new Contact()));
    List<Contact> result = adminService.getDoneContactRequests();
    assertEquals(3, result.size());
  }

  @Test
  void getContactByIdWhenNoDataExpectException() {
    when(contactRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.getContactById(1));
  }

  @Test
  void getContactByIdWhenDataExpectContactReturned() {
    Contact contact = new Contact();
    when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contact));
    Contact result = adminService.getContactById(1);
    assertEquals(contact, result);
  }

  @Test
  void markContactRequestAsDoneWhenNoDataExpectException() {
    when(contactRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markContactRequestAsDone(1));
  }

  @Test
  void markContactRequestAsDoneWhenDataExpectDoneTrue() {
    Contact contact = new Contact();
    contact.setDone(false);
    when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contact));
    adminService.markContactRequestAsDone(1);
    assertEquals(true, contact.isDone());
  }

  @Test
  void markContactRequestAsUndoneWhenNoDataExpectException() {
    when(contactRepository.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(RuntimeException.class, () -> adminService.markContactRequestAsUndone(1));
  }

  @Test
  void markContactRequestAsUndoneWhenDataExpectDoneFalse() {
    Contact contact = new Contact();
    contact.setDone(true);
    when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contact));
    adminService.markContactRequestAsUndone(1);
    assertEquals(false, contact.isDone());
  }
}
