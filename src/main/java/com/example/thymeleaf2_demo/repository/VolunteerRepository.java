package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.Volunteer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class VolunteerRepository {

    private final JdbcTemplate jdbcTemplate;

    public VolunteerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Volunteer> volunteerRowMapper = (rs, rowNum) -> {
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerId(rs.getInt("volunteer_id"));
        volunteer.setUsername(rs.getString("username"));
        volunteer.setFirstName(rs.getString("first_name"));
        volunteer.setMiddleName(rs.getString("middle_name"));
        volunteer.setLastName(rs.getString("last_name"));
        volunteer.setEmail(rs.getString("email"));
        volunteer.setPassword(rs.getString("password"));
        volunteer.setCity(rs.getString("city"));
        volunteer.setStreetAddress(rs.getString("street_address"));
        volunteer.setHouseNumber(rs.getString("house_number"));
        volunteer.setApartment(rs.getString("apartment"));
        volunteer.setPhoneNumber(rs.getString("phone_number"));
        return volunteer;
    };

    public void save(Volunteer volunteer) {
        String sql = "INSERT INTO Volunteer (username, first_name, middle_name, last_name, email, password, city, " +
                "street_address, house_number, apartment, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, volunteer.getUsername(), volunteer.getFirstName(), volunteer.getMiddleName(),
                volunteer.getLastName(), volunteer.getEmail(), volunteer.getPassword(), volunteer.getCity(),
                volunteer.getStreetAddress(), volunteer.getHouseNumber(), volunteer.getApartment(),
                volunteer.getPhoneNumber());
    }

    public Optional<Volunteer> findByUsername(String username) {
        String sql = "SELECT * FROM Volunteer WHERE username = ?";
        return jdbcTemplate.query(sql, volunteerRowMapper, username).stream().findFirst();
    }

    public Optional<Volunteer> findByEmail(String email) {
        String sql = "SELECT * FROM Volunteer WHERE email = ?";
        return jdbcTemplate.query(sql, volunteerRowMapper, email).stream().findFirst();
    }
}
