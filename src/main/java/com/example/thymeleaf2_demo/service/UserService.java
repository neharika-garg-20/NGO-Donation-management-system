
package com.example.thymeleaf2_demo.service;

import com.example.thymeleaf2_demo.model.*;
import com.example.thymeleaf2_demo.repository.DonorRepository;
import com.example.thymeleaf2_demo.repository.NgoRepository;
import com.example.thymeleaf2_demo.repository.PickupAgentRepository;
import com.example.thymeleaf2_demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private NgoRepository ngoRepository;
    @Autowired
    private PickupAgentRepository pickupAgentRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;

    
    public void registerUser(User user) {
        switch (user.getUserType()) {
            case "Donor":
                Donor donor = new Donor();
                copyCommonFields(user, donor);
                donor.setBadgeLevel("Silver");
                donor.setTotalDonations(0);
                donorRepository.save(donor);
                break;
            case "NGO":
                NGO ngo = new NGO();
                copyCommonFields(user, ngo);
                if (user.getOrganizationName() == null || user.getOrganizationName().trim().isEmpty()) {
                    throw new IllegalArgumentException("Organization name is required for NGO");
                }
                if (user.getAcceptedItemTypes() == null || user.getAcceptedItemTypes().isEmpty()) {
                    throw new IllegalArgumentException("At least one accepted item type is required for NGO");
                }
                if (user.getRegistrationId() == null || user.getRegistrationId().trim().isEmpty()) {
                    throw new IllegalArgumentException("Registration ID is required for NGO");
                }
                if (user.getDocumentUrl() == null || user.getDocumentUrl().isEmpty()) {
                    throw new IllegalArgumentException("Verification document is required for NGO");
                }
                ngo.setOrganizationName(user.getOrganizationName());
                ngo.setAcceptedItemTypes(user.getAcceptedItemTypes());
                ngo.setWebsiteUrl(user.getWebsiteUrl());
                ngo.setUpiId(user.getUpiId());
                ngo.setBankAccountNumber(user.getBankAccountNumber());
                ngo.setBankIfsc(user.getBankIfsc());
                ngo.setBankAccountName(user.getBankAccountName());
                ngo.setRegistrationId(user.getRegistrationId());
                ngo.setDocumentUrl(user.getDocumentUrl());
                ngoRepository.save(ngo); // Save to NGO table first to get ngo_id
                ngoRepository.saveToVerification(ngo); // Then save to NGO_verification
                break;
            case "Pickup_Agent":
                PickupAgent agent = new PickupAgent();
                copyCommonFields(user, agent);
                agent.setVehicleType(user.getVehicleType());
                agent.setStatus("free");
                pickupAgentRepository.save(agent);
                break;
            case "Volunteer":
                Volunteer volunteer = new Volunteer();
                copyCommonFields(user, volunteer);
                volunteerRepository.save(volunteer);
                break;
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }

    private void copyCommonFields(User user, Object target) {
        if (target instanceof Donor) {
            Donor donor = (Donor) target;
            donor.setUsername(user.getUsername());
            donor.setFirstName(user.getFirstName());
            donor.setMiddleName(user.getMiddleName());
            donor.setLastName(user.getLastName());
            donor.setEmail(user.getEmail());
            donor.setPassword(user.getPassword());
            donor.setCity(user.getCity());
            donor.setStreetAddress(user.getStreetAddress());
            donor.setHouseNumber(user.getHouseNumber());
            donor.setApartment(user.getApartment());
            donor.setPhoneNumber(user.getPhoneNumber());
        } else if (target instanceof NGO) {
            NGO ngo = (NGO) target;
            ngo.setUsername(user.getUsername());
            ngo.setFirstName(user.getFirstName());
            ngo.setMiddleName(user.getMiddleName());
            ngo.setLastName(user.getLastName());
            ngo.setEmail(user.getEmail());
            ngo.setPassword(user.getPassword());
            ngo.setCity(user.getCity());
            ngo.setStreetAddress(user.getStreetAddress());
            ngo.setHouseNumber(user.getHouseNumber());
            ngo.setApartment(user.getApartment());
            ngo.setPhoneNumber(user.getPhoneNumber());
        } else if (target instanceof PickupAgent) {
            PickupAgent agent = (PickupAgent) target;
            agent.setUsername(user.getUsername());
            agent.setFirstName(user.getFirstName());
            agent.setMiddleName(user.getMiddleName());
            agent.setLastName(user.getLastName());
            agent.setEmail(user.getEmail());
            agent.setPassword(user.getPassword());
            agent.setCity(user.getCity());
            agent.setStreetAddress(user.getStreetAddress());
            agent.setHouseNumber(user.getHouseNumber());
            agent.setApartment(user.getApartment());
            agent.setPhoneNumber(user.getPhoneNumber());
        } else if (target instanceof Volunteer) {
            Volunteer volunteer = (Volunteer) target;
            volunteer.setUsername(user.getUsername());
            volunteer.setFirstName(user.getFirstName());
            volunteer.setMiddleName(user.getMiddleName());
            volunteer.setLastName(user.getLastName());
            volunteer.setEmail(user.getEmail());
            volunteer.setPassword(user.getPassword());
            volunteer.setCity(user.getCity());
            volunteer.setStreetAddress(user.getStreetAddress());
            volunteer.setHouseNumber(user.getHouseNumber());
            volunteer.setApartment(user.getApartment());
            volunteer.setPhoneNumber(user.getPhoneNumber());
}
}

public boolean login(User user) {
    String email = user.getEmail();
    String password = user.getPassword();

    String userType = user.getUserType().toLowerCase();
System.out.println("UserType: " + userType);
System.out.println("email: " + email);
System.out.println("password: " + password);


switch (userType) {
    case "donor":

return donorRepository.findByEmail(email)
      .map(d -> {
          System.out.println("DB password: " + d.getPassword());
          System.out.println("Entered password: " + password);
          System.out.println("Match? " + d.getPassword().equals(password));
          return d.getPassword().equals(password);
      })
      .orElse(false);
    case "ngo":
        return ngoRepository.findByEmail(email)
                            .map(n -> n.getPassword().equals(password))
                            .orElse(false);
    case "pickup_agent":
        return pickupAgentRepository.findByEmail(email)
                                    .map(a -> a.getPassword().equals(password))
                                    .orElse(false);
    case "volunteer":
        return volunteerRepository.findByEmail(email)
                                  .map(v -> v.getPassword().equals(password))
                                  .orElse(false);
    default:
        return false;
}

}



   public Optional<Donor> getDonorByUsername(String username) {
        return donorRepository.findByUsername(username);
    }

    public Optional<Donor> getDonorByEmail(String email) {
        return donorRepository.findByEmail(email);
    }

    public Optional<NGO> getNgoByUsername(String username) {
        return ngoRepository.findByUsername(username);
    }

    public Optional<NGO> getNgoByEmail(String email) {
        return ngoRepository.findByEmail(email);
    }

    public Optional<PickupAgent> getPickupAgentByUsername(String username) {
        return pickupAgentRepository.findByUsername(username);
    }

    public Optional<PickupAgent> getPickupAgentByEmail(String email) {
        return pickupAgentRepository.findByEmail(email);
    }


    public Optional<Volunteer> getVolunteerByUsername(String username) {
        return volunteerRepository.findByUsername(username);
    }

    public Optional<Volunteer> getVolunteerByEmail(String email) {
        return volunteerRepository.findByEmail(email);
    }
}
