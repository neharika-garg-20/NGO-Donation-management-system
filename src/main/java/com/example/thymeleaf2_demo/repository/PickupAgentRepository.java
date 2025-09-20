package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.PickupAgent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;


@Repository
public class PickupAgentRepository {
    private static final Logger logger = LoggerFactory.getLogger(PickupAgentRepository.class);


    private final JdbcTemplate jdbcTemplate;

    public PickupAgentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PickupAgent> agentRowMapper = (rs, rowNum) -> {
        PickupAgent agent = new PickupAgent();
        agent.setAgentId(rs.getInt("agent_id"));
        agent.setUsername(rs.getString("username"));
        agent.setFirstName(rs.getString("first_name"));
        agent.setMiddleName(rs.getString("middle_name"));
        agent.setLastName(rs.getString("last_name"));
        agent.setEmail(rs.getString("email"));
        agent.setPassword(rs.getString("password"));
        agent.setCity(rs.getString("city"));
        agent.setStreetAddress(rs.getString("street_address"));
        agent.setHouseNumber(rs.getString("house_number"));
        agent.setApartment(rs.getString("apartment"));
        agent.setPhoneNumber(rs.getString("phone_number"));
        agent.setVehicleType(rs.getString("vehicle_type"));
        agent.setStatus(rs.getString("status"));
        return agent;
    };

    public int save(PickupAgent agent) {
        String sql = """
            INSERT INTO pickup_agent 
            (username, first_name, middle_name, last_name, email, password, city, street_address, 
             house_number, apartment, phone_number, vehicle_type, status) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, agent.getUsername());
            ps.setString(2, agent.getFirstName());
            ps.setString(3, agent.getMiddleName());
            ps.setString(4, agent.getLastName());
            ps.setString(5, agent.getEmail());
  ps.setString(6, agent.getPassword());
            ps.setString(7, agent.getCity());
            ps.setString(8, agent.getStreetAddress());
            ps.setString(9, agent.getHouseNumber());
            ps.setString(10, agent.getApartment());
            ps.setString(11, agent.getPhoneNumber());
            ps.setString(12, agent.getVehicleType());
            ps.setString(13, agent.getStatus());
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            Number key = keyHolder.getKey();
            if (key != null) {
                agent.setAgentId(key.intValue());
                logger.info("Pickup Agent saved with agent_id: {}", agent.getAgentId());
            }
        } else {
            logger.error("Failed to insert Pickup Agent into database");
        }

        return rowsAffected;
    }



    public Optional<PickupAgent> findByUsername(String username) {
        String sql = "SELECT * FROM Pickup_Agent WHERE username = ?";
        return jdbcTemplate.query(sql, agentRowMapper, username).stream().findFirst();
    }

    public Optional<PickupAgent> findByEmail(String email) {
        String sql = "SELECT * FROM Pickup_Agent WHERE email = ?";
        return jdbcTemplate.query(sql, agentRowMapper, email).stream().findFirst();
    }
}
