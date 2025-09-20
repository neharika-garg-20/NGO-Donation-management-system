// package com.example.thymeleaf2_demo;

// import com.example.thymeleaf2_demo.model.Donor;
// import com.example.thymeleaf2_demo.service.DonorService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import javax.servlet.http.HttpSession;
// import java.util.Map;

// @Controller
// public class DonorController {

//     @Autowired
//     private DonorService donorService;

//     @GetMapping("/donor_dashboard")
//     public String getDonorDashboard(HttpSession session, Model model) {
//         String email = (String) session.getAttribute("userEmail");
//         if (email == null) {
//             return "redirect:/login";
//         }

//         Map<String, Object> data = donorService.getDonorDashboardData(email);
//         if (data == null) {
//             model.addAttribute("errorMessage", "Donor not found");
//             return "error";
//         }

//         model.addAttribute("donor", data.get("donor"));
//         model.addAttribute("donationCategories", data.get("donationCategories"));
//         model.addAttribute("ngoCount", data.get("ngoCount"));
//         model.addAttribute("initials", data.get("initials"));
//         model.addAttribute("badgeNote", data.get("badgeNote"));

//         return "donor_dashboard";
//     }

//     @PostMapping("/donor/update-profile")
//     public String updateDonorProfile(
//             @RequestParam String email,
//             @RequestParam String phone,
//             @RequestParam String address,
//             @RequestParam String city,
//             HttpSession session,
//             Model model) {
//         String currentEmail = (String) session.getAttribute("userEmail");
//         if (currentEmail == null) {
//             return "redirect:/login";
//         }
//         public boolean updateDonorProfile(String currentEmail, String newEmail, String phone, String address, String city) {
//     Donor donor = donorRepository.findByEmail(currentEmail);
//     if (donor == null) {
//         return false;
//     }

//     String[] addressParts = address.split(",");
//     String streetAddress = addressParts[0].trim();
//     String houseNumber = addressParts.length > 1 ? addressParts[1].trim() : null;
//     String apartment = addressParts.length > 2 ? addressParts[2].trim() : null;

//     String sql = "UPDATE Donor SET email = ?, phone_number = ?, street_address = ?, house_number = ?, apartment = ?, city = ? WHERE email = ?";
//     int rows = jdbcTemplate.update(sql, newEmail, phone, streetAddress, houseNumber, apartment, city, currentEmail);

//     if (rows > 0) {
//         donor.setEmail(newEmail);
//         donor.setPhoneNumber(phone);
//         donor.setStreetAddress(streetAddress);
//         donor.setHouseNumber(houseNumber);
//         donor.setApartment(apartment);
//         donor.setCity(city);
//         return true;
//     }
//     return false;
// }

//         boolean success = donorService.updateDonorProfile(currentEmail, email, phone, address, city);
//         if (success) {
//             model.addAttribute("successMessage", "Saved! Your profile was updated.");
//             if (!currentEmail.equals(email)) {
//                 session.setAttribute("userEmail", email);
//             }
//         } else {
//             model.addAttribute("errorMessage", "Failed to update profile.");
//         }

//         // Re-fetch data with the updated email
//         Map<String, Object> data = donorService.getDonorDashboardData(email);
//         if (data == null) {
//             model.addAttribute("errorMessage", "Donor not found");
//             return "error";
//         }

//         model.addAttribute("donor", data.get("donor"));
//         model.addAttribute("donationCategories", data.get("donationCategories"));
//         model.addAttribute("ngoCount", data.get("ngoCount"));
//         model.addAttribute("initials", data.get("initials"));
//         model.addAttribute("badgeNote", data.get("badgeNote"));

//         return "donor_dashboard";
//     }

//     public static class DonorViewModel {
//         private final Donor donor;

//         public DonorViewModel(Donor donor) {
//             this.donor = donor;
//         }

//         public Integer getDonorId() { return donor.getDonorId(); }
//         public String getUsername() { return donor.getUsername(); }
//         public String getName() {
//             return donor.getFirstName() + 
//                    (donor.getMiddleName() != null ? " " + donor.getMiddleName() : "") + 
//                    " " + donor.getLastName();
//         }
//         public String getEmail() { return donor.getEmail(); }
//         public String getPhoneNumber() { return donor.getPhoneNumber(); }
//         public String getStreetAddress() { return donor.getStreetAddress(); }
//         public String getHouseNumber() { return donor.getHouseNumber(); }
//         public String getApartment() { return donor.getApartment(); }
//         public String getCity() { return donor.getCity(); }
//         public String getBadgeLevel() { return donor.getBadgeLevel(); }
//         public Integer getTotalDonations() { return donor.getTotalDonations(); }
//     }
// }