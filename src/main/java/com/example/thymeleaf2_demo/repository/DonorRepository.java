package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DonorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Donor donor) {
        String sql = "INSERT INTO Donor " +
                "(username, first_name, middle_name, last_name, email, password, city, street_address, house_number, apartment, phone_number, badge_level, total_donations) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                donor.getUsername(),
                donor.getFirstName(),
                donor.getMiddleName(),
                donor.getLastName(),
                donor.getEmail(),
                donor.getPassword(),
                donor.getCity(),
                donor.getStreetAddress(),
                donor.getHouseNumber(),
                donor.getApartment(),
                donor.getPhoneNumber(),
                donor.getBadgeLevel() != null ? donor.getBadgeLevel() : "Silver",
                donor.getTotalDonations() != null ? donor.getTotalDonations() : 0
        );
    }

    public Donor findByEmail(String email) {
        String sql = "SELECT * FROM Donor WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Donor d = new Donor();
            d.setDonorId(rs.getInt("donor_id"));
            d.setUsername(rs.getString("username"));
            d.setFirstName(rs.getString("first_name"));
            d.setMiddleName(rs.getString("middle_name"));
            d.setLastName(rs.getString("last_name"));
            d.setEmail(rs.getString("email"));
            d.setPassword(rs.getString("password"));
            d.setCity(rs.getString("city"));
            d.setStreetAddress(rs.getString("street_address"));
            d.setHouseNumber(rs.getString("house_number"));
            d.setApartment(rs.getString("apartment"));
            d.setPhoneNumber(rs.getString("phone_number"));
            d.setBadgeLevel(rs.getString("badge_level"));
            d.setTotalDonations(rs.getInt("total_donations"));
            return d;
        }, email);
    }
}
