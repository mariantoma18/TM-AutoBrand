package TM.controller;

import TM.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final AdminService adminService;

  @GetMapping("/homePage")
  public String getAdminHomePage() {
    return "adminHomePage";
  }

  @GetMapping("/viewOrders")
  public String getOrdersPage(Model model) {
    model.addAttribute("orders", adminService.getActiveOrders());
    return "adminViewOrders";
  }

  @PostMapping("/viewOrders/markOrderAsDone")
  public String markAsDoneOrder(@RequestParam int orderId) {
    adminService.markOrderAsDone(orderId);
    return "redirect:/admin/viewOrders";
  }

  @GetMapping("/viewOrders/done")
  public String getDoneOrdersPage(Model model) {
    model.addAttribute("doneOrders", adminService.getDoneOrders());
    return "adminViewDoneOrders";
  }

  @PostMapping("/viewOrders/markOrderAsUndone")
  public String markAsUndoneOrder(@RequestParam int orderId) {
    adminService.markOrderAsUndone(orderId);
    return "redirect:/admin/viewOrders/done";
  }

  @GetMapping("/viewOrderDetails")
  public String getOrderDetails(@RequestParam int orderId, Model model) {
    model.addAttribute("order", adminService.getOrderById(orderId));
    model.addAttribute("items", adminService.getOrderById(orderId).getOrderItems());
    return "adminOrderDetails";
  }

  @GetMapping("/serviceAppointments")
  public String getServiceAppointmentsPage(Model model) {
    model.addAttribute("serviceAppointments", adminService.getActiveServiceRequests());
    return "adminServiceAppointments";
  }

  @PostMapping("/serviceAppointments/markAsDone")
  public String markAsDoneServiceRequest(@RequestParam int requestId) {
    adminService.markServiceRequestAsDone(requestId);
    return "redirect:/admin/serviceAppointments";
  }

  @GetMapping("/serviceAppointments/done")
  public String getDoneServiceRequestsPage(Model model) {
    model.addAttribute("doneServiceAppointments", adminService.getDoneServiceRequests());
    return "adminViewDoneServiceRequests";
  }

  @PostMapping("/serviceAppointments/markAsUndone")
  public String markAsUndoneServiceRequest(@RequestParam int requestId) {
    adminService.markServiceRequestAsUndone(requestId);
    return "redirect:/admin/serviceAppointments/done";
  }

  @GetMapping("/testDriveRequests")
  public String getTestDriveRequestsPage(Model model) {
    model.addAttribute("testDriveRequests", adminService.getActiveTestDriveRequests());
    return "adminTestDriveRequests";
  }

  @PostMapping("/testDriveRequests/markAsDone")
  public String markAsDoneTestDriveRequest(@RequestParam int requestId) {
    adminService.markTestDriveRequestAsDone(requestId);
    return "redirect:/admin/testDriveRequests";
  }

  @GetMapping("/testDriveRequests/done")
  public String getDoneTestDriveRequestsPage(Model model) {
    model.addAttribute("doneTestDriveRequests", adminService.getDoneTestDriveRequests());
    return "adminViewDoneTestDriveRequests";
  }

  @PostMapping("/testDriveRequests/markAsUndone")
  public String markAsUndoneTestDriveRequest(@RequestParam int requestId) {
    adminService.markTestDriveRequestAsUndone(requestId);
    return "redirect:/admin/testDriveRequests/done";
  }

  @GetMapping("/contactRequests")
  public String getContactRequestsPage(Model model) {
    model.addAttribute("contactRequests", adminService.getAllContactRequests());
    return "adminContactRequests";
  }

  @GetMapping("/dashboard")
  public String getDashboardPage() {
    return "adminDashboard";
  }
}
