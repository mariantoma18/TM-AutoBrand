package TM.service;

import TM.model.DashboardStats;
import TM.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardStatsService {

  private final OfferRequestRepository offerRequestRepository;
  private final SedanRepository sedanRepository;
  private final SuvRepository suvRepository;
  private final ServiceRequestRepository serviceRequestRepository;
  private final TestDriveRepository testDriveRepository;
  private final ContactRepository contactRepository;
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  public DashboardStats mapStatsToDashboardStats() {
    return new DashboardStats(
        countTotalConfiguredModels(),
        countConfiguredSedans(),
        countConfiguredSuvs(),
        countTotalOfferRequests(),
        countSedansOfferRequests(),
        countSuvsOfferRequests(),
        countServiceRequests(),
        countTestDriveRequests(),
        countContactRequests(),
        countTotalOrders(),
        countActiveOrders(),
        countDoneOrders(),
        countTotalUsers());
  }

  public long countTotalConfiguredModels() {
    long sedans = countConfiguredSedans();
    long suvs = countConfiguredSuvs();

    return sedans + suvs;
  }

  public long countConfiguredSedans() {
    return sedanRepository.count();
  }

  public long countConfiguredSuvs() {
    return suvRepository.count();
  }

  public long countTotalOfferRequests() {
    return offerRequestRepository.count();
  }

  public long countSedansOfferRequests() {
    return offerRequestRepository.countBySedanIsNotNull();
  }

  public long countSuvsOfferRequests() {
    return offerRequestRepository.countBySuvIsNotNull();
  }

  public long countServiceRequests() {
    return serviceRequestRepository.count();
  }

  public long countTestDriveRequests() {
    return testDriveRepository.count();
  }

  public long countContactRequests() {
    return contactRepository.count();
  }

  public long countTotalOrders() {
    return orderRepository.count();
  }

  public long countActiveOrders() {
    return orderRepository.countByIsDoneFalse();
  }

  public long countDoneOrders() {
    return orderRepository.countByIsDoneTrue();
  }

  public long countTotalUsers() {
    return userRepository.count();
  }
}
