package TM.controller;

import TM.model.Order;
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

  @PostMapping("viewOrders/markAsDone")
  public String markAsDoneOrder(@RequestParam int orderId) {
    adminService.markAsDone(orderId);
    return "redirect:/admin/viewOrders";
  }

  @GetMapping("/viewOrders/done")
  public String getDoneOrdersPage(Model model) {
    model.addAttribute("doneOrders", adminService.getDoneOrders());
    return "adminViewDoneOrders";
  }

  @PostMapping("viewOrders/markAsUndone")
  public String markAsUndoneOrder(@RequestParam int orderId) {
    adminService.markAsUndone(orderId);
    return "redirect:/admin/viewOrders/done";
  }

  @GetMapping("/serviceAppointments")
  public String getServiceAppointmentsPage(Model model) {
    model.addAttribute("serviceAppointments", adminService.getAllServiceRequests());
    return "adminServiceAppointments";
  }

  @GetMapping("/testDriveRequests")
  public String getTestDriveRequestsPage(Model model) {
    model.addAttribute("testDriveRequests", adminService.getAllTestDriveRequests());
    return "adminTestDriveRequests";
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
