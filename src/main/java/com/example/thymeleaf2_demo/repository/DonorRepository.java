package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.Donor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// @Repository
// public class DonorRepository {

//     @Autowired
//     private JdbcTemplate jdbcTemplate;

//     public int save(Donor donor) {
//         String sql = "INSERT INTO Donor " +
//                 "(username, first_name, middle_name, last_name, email, password, city, street_address, house_number, apartment, phone_number, badge_level, total_donations) " +
//                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//         return jdbcTemplate.update(sql,
//                 donor.getUsername(),
//                 donor.getFirstName(),
//                 donor.getMiddleName(),
//                 donor.getLastName(),
//                 donor.getEmail(),
//                 donor.getPassword(),
//                 donor.getCity(),
//                 donor.getStreetAddress(),
//                 donor.getHouseNumber(),
//                 donor.getApartment(),
//                 donor.getPhoneNumber(),
//                 donor.getBadgeLevel() != null ? donor.getBadgeLevel() : "Silver",
//                 donor.getTotalDonations() != null ? donor.getTotalDonations() : 0
//         );
//     }

//     public Donor findByEmail(String email) {
//         String sql = "SELECT * FROM Donor WHERE email = ?";
//         return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//             Donor d = new Donor();
//             d.setDonorId(rs.getInt("donor_id"));
//             d.setUsername(rs.getString("username"));
//             d.setFirstName(rs.getString("first_name"));
//             d.setMiddleName(rs.getString("middle_name"));
//             d.setLastName(rs.getString("last_name"));
//             d.setEmail(rs.getString("email"));
//             d.setPassword(rs.getString("password"));
//             d.setCity(rs.getString("city"));
//             d.setStreetAddress(rs.getString("street_address"));
//             d.setHouseNumber(rs.getString("house_number"));
//             d.setApartment(rs.getString("apartment"));
//             d.setPhoneNumber(rs.getString("phone_number"));
//             d.setBadgeLevel(rs.getString("badge_level"));
//             d.setTotalDonations(rs.getInt("total_donations"));
//             return d;
//         }, email);
//     }
// }


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class DonorRepository {

    private final JdbcTemplate jdbcTemplate;

    public DonorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Donor> donorRowMapper = (rs, rowNum) -> {
        Donor donor = new Donor();
        donor.setDonorId(rs.getInt("donor_id"));
        donor.setUsername(rs.getString("username"));
        donor.setFirstName(rs.getString("first_name"));
        donor.setMiddleName(rs.getString("middle_name"));
        donor.setLastName(rs.getString("last_name"));
        donor.setEmail(rs.getString("email"));
        donor.setPassword(rs.getString("password"));
        donor.setCity(rs.getString("city"));
        donor.setStreetAddress(rs.getString("street_address"));
        donor.setHouseNumber(rs.getString("house_number"));
        donor.setApartment(rs.getString("apartment"));
        donor.setPhoneNumber(rs.getString("phone_number"));
        donor.setBadgeLevel(rs.getString("badge_level"));
        donor.setTotalDonations(rs.getInt("total_donations"));
        return donor;
    };

    public void save(Donor donor) {
        String sql = "INSERT INTO Donor (username, first_name, middle_name, last_name, email, password, city, " +
                "street_address, house_number, apartment, phone_number, badge_level, total_donations) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, donor.getUsername(), donor.getFirstName(), donor.getMiddleName(),
                donor.getLastName(), donor.getEmail(), donor.getPassword(), donor.getCity(),
                donor.getStreetAddress(), donor.getHouseNumber(), donor.getApartment(),
                donor.getPhoneNumber(), donor.getBadgeLevel(), donor.getTotalDonations());
    }

    public Optional<Donor> findByUsername(String username) {
        String sql = "SELECT * FROM Donor WHERE username = ?";
        return jdbcTemplate.query(sql, donorRowMapper, username).stream().findFirst();
    }

    public Optional<Donor> findByEmail(String email) {
    String sql = "SELECT * FROM Donor WHERE email = ?";
    System.out.println("sql is: " + sql);
    Optional<Donor> donorOpt = jdbcTemplate.query(sql, donorRowMapper, email).stream().findFirst();
System.out.println("Donor found for " + email + "? " + donorOpt.isPresent());
return donorOpt;
   
}

}
