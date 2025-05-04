package TM.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStats {

  private long totalConfiguredModels; // sum of next 2
  private long configuredElyssions;
  private long configuredTeraons;

  private long totalOfferRequests; // sum of next 2
  private long elyssionOfferRequests;
  private long teraonOfferRequests;

  private long serviceRequests;
  private long testDriveRequests;
  private long contactRequests;
  private long orders; // accessory orders
  private long activeOrders;
  private long doneOrders;
  private long totalUsers;
}
