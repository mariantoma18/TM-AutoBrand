package TM.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import TM.model.DashboardStats;
import TM.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DashboardStatsServiceTest {

  @Mock private OfferRequestRepository offerRequestRepository;

  @Mock private SedanRepository sedanRepository;

  @Mock private SuvRepository suvRepository;

  @Mock private ServiceRequestRepository serviceRequestRepository;

  @Mock private TestDriveRepository testDriveRepository;

  @Mock private ContactRepository contactRepository;

  @Mock private OrderRepository orderRepository;

  @Mock private UserRepository userRepository;

  @InjectMocks private DashboardStatsService dashboardStatsService;

  @Test
  void mapStatsToDashboardStats() {
    when(dashboardStatsService.countTotalConfiguredModels()).thenReturn(10L);
    when(dashboardStatsService.countConfiguredSedans()).thenReturn(12L);
    when(dashboardStatsService.countConfiguredSuvs()).thenReturn(15L);
    when(dashboardStatsService.countTotalOfferRequests()).thenReturn(20L);
    when(dashboardStatsService.countSedansOfferRequests()).thenReturn(8L);
    when(dashboardStatsService.countSuvsOfferRequests()).thenReturn(10L);
    when(dashboardStatsService.countServiceRequests()).thenReturn(10L);
    when(dashboardStatsService.countTestDriveRequests()).thenReturn(25L);
    when(dashboardStatsService.countContactRequests()).thenReturn(3L);
    when(dashboardStatsService.countTotalOrders()).thenReturn(30L);
    when(dashboardStatsService.countActiveOrders()).thenReturn(5L);
    when(dashboardStatsService.countDoneOrders()).thenReturn(25L);
    when(dashboardStatsService.countTotalUsers()).thenReturn(4L);

    DashboardStats dashboardStats = dashboardStatsService.mapStatsToDashboardStats();

    assertEquals(4, dashboardStats.getTotalUsers());
  }

  @Test
  void countTotalConfiguredModels() {
    when(dashboardStatsService.countConfiguredSedans()).thenReturn(2L);
    when(dashboardStatsService.countConfiguredSuvs()).thenReturn(3L);

    long result = dashboardStatsService.countTotalConfiguredModels();

    assertEquals(5, result);
  }

  @Test
  void countConfiguredSedans() {
    when(sedanRepository.count()).thenReturn(4L);

    long result = dashboardStatsService.countConfiguredSedans();

    assertEquals(4, result);
  }

  @Test
  void countConfiguredSuvs() {
    when(suvRepository.count()).thenReturn(10L);

    long result = dashboardStatsService.countConfiguredSuvs();

    assertEquals(10, result);
  }

  @Test
  void countTotalOfferRequests() {
    when(offerRequestRepository.count()).thenReturn(20L);

    long result = dashboardStatsService.countTotalOfferRequests();

    assertEquals(20, result);
  }

  @Test
  void countSedansOfferRequests() {
    when(offerRequestRepository.countBySedanIsNotNull()).thenReturn(2L);

    long result = dashboardStatsService.countSedansOfferRequests();

    assertEquals(2, result);
  }

  @Test
  void countSuvsOfferRequests() {
    when(offerRequestRepository.countBySuvIsNotNull()).thenReturn(5L);

    long result = dashboardStatsService.countSuvsOfferRequests();

    assertEquals(5, result);
  }

  @Test
  void countServiceRequests() {
    when(serviceRequestRepository.count()).thenReturn(13L);

    long result = dashboardStatsService.countServiceRequests();

    assertEquals(13, result);
  }

  @Test
  void countTestDriveRequests() {
    when(testDriveRepository.count()).thenReturn(18L);

    long result = dashboardStatsService.countTestDriveRequests();

    assertEquals(18, result);
  }

  @Test
  void countContactRequests() {
    when(contactRepository.count()).thenReturn(9L);

    long result = dashboardStatsService.countContactRequests();

    assertEquals(9, result);
  }

  @Test
  void countTotalOrders() {
    when(orderRepository.count()).thenReturn(33L);

    long result = dashboardStatsService.countTotalOrders();

    assertEquals(33, result);
  }

  @Test
  void countActiveOrders() {
    when(orderRepository.countByIsDoneFalse()).thenReturn(14L);

    long result = dashboardStatsService.countActiveOrders();

    assertEquals(14, result);
  }

  @Test
  void countDoneOrders() {
    when(orderRepository.countByIsDoneTrue()).thenReturn(6L);

    long result = dashboardStatsService.countDoneOrders();

    assertEquals(6, result);
  }

  @Test
  void countTotalUsers() {
    when(userRepository.count()).thenReturn(2L);

    long result = dashboardStatsService.countTotalUsers();

    assertEquals(2, result);
  }
}
