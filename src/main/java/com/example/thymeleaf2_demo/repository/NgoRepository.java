package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.NGO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.Optional;


import com.example.thymeleaf2_demo.model.NGO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Repository
public class NGORepository {

    private static final Logger logger = LoggerFactory.getLogger(NGORepository.class);


    private final JdbcTemplate jdbcTemplate;

    public NGORepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<NGO> ngoRowMapper = (rs, rowNum) -> {
        NGO ngo = new NGO();
        ngo.setNgoId(rs.getInt("ngo_id"));
        ngo.setUsername(rs.getString("username"));
        ngo.setFirstName(rs.getString("first_name"));
        ngo.setMiddleName(rs.getString("middle_name"));
        ngo.setLastName(rs.getString("last_name"));
        ngo.setEmail(rs.getString("email"));
        ngo.setPassword(rs.getString("password"));
        ngo.setCity(rs.getString("city"));
        ngo.setStreetAddress(rs.getString("street_address"));
        ngo.setHouseNumber(rs.getString("house_number"));
        ngo.setApartment(rs.getString("apartment"));
        ngo.setPhoneNumber(rs.getString("phone_number"));
        ngo.setOrganizationName(rs.getString("organization_name"));
        String acceptedItemsStr = rs.getString("accepted_item_types");
Set<String> acceptedItems = new HashSet<>();
if (acceptedItemsStr != null && !acceptedItemsStr.isEmpty()) {
    for (String item : acceptedItemsStr.split(",")) {
        acceptedItems.add(item.trim());
    }
}
ngo.setAcceptedItemTypes(acceptedItems);
        ngo.setWebsiteUrl(rs.getString("website_url"));
        ngo.setUpiId(rs.getString("upi_id"));
        ngo.setBankAccountNumber(rs.getString("bank_account_number"));
        ngo.setBankIfsc(rs.getString("bank_ifsc"));
        ngo.setBankAccountName(rs.getString("bank_account_name"));
        return ngo;
    };

 public int save(NGO ngo) {
        try {
            String sql = "INSERT INTO NGO " +
                    "(username, first_name, middle_name, last_name, email, password, city, street_address, house_number, apartment, phone_number, organization_name, accepted_item_types, website_url, upi_id, bank_account_number, bank_ifsc, bank_account_name) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
   ps.setString(1, ngo.getUsername());
                ps.setString(2, ngo.getFirstName());
                ps.setString(3, ngo.getMiddleName());
                ps.setString(4, ngo.getLastName());
                ps.setString(5, ngo.getEmail());
                ps.setString(6, ngo.getPassword());
                ps.setString(7, ngo.getCity());
                ps.setString(8, ngo.getStreetAddress());
                ps.setString(9, ngo.getHouseNumber());
                ps.setString(10, ngo.getApartment());
                ps.setString(11, ngo.getPhoneNumber());
                ps.setString(12, ngo.getOrganizationName());
                ps.setString(13, ngo.getAcceptedItemTypes() != null ? String.join(",", ngo.getAcceptedItemTypes()) : null);
                ps.setString(14, ngo.getWebsiteUrl());
                ps.setString(15, ngo.getUpiId());
                ps.setString(16, ngo.getBankAccountNumber());
                ps.setString(17, ngo.getBankIfsc());
                ps.setString(18, ngo.getBankAccountName());
                return ps;
            }, keyHolder);
             if (rowsAffected > 0) {
                Number key = keyHolder.getKey();
                if (key != null) {
                    ngo.setNgoId(key.intValue());
                    logger.info("NGO saved with ngo_id: {}", ngo.getNgoId());
                } else {
                    logger.error("Failed to retrieve ngo_id after insert");
                    throw new RuntimeException("Failed to retrieve generated ngo_id");
                }
            } else {
                logger.error("Failed to insert NGO into database");
                throw new RuntimeException("Failed to insert NGO");
            }
            return rowsAffected;
        } catch (Exception e) {
            logger.error("Error saving NGO: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving NGO: " + e.getMessage(), e);
        }
    }
   public int saveToVerification(NGO ngo) {
        try {
            if (ngo.getNgoId() == 0) {
                logger.error("NGO ID is 0, cannot save to NGO_verification");
                throw new IllegalArgumentException("NGO ID is not set");
            }
            String sql = "INSERT INTO NGO_verification " +
                    "(ngo_id, registration_number, document_url, date_submitted, status, verified_by_admin_id) " +
                    "VALUES (?, ?, ?, ?, 'Pending', NULL)";
            int rowsAffected = jdbcTemplate.update(sql,
                    ngo.getNgoId(),
                    ngo.getRegistrationId(),
                    ngo.getDocumentUrl(),
                    LocalDate.now()
            );
            if (rowsAffected > 0) {
                logger.info("NGO verification saved for ngo_id: {}", ngo.getNgoId());
            } else {
                logger.error("Failed to insert into NGO_verification for ngo_id: {}", ngo.getNgoId());
                                throw new RuntimeException("Failed to insert into NGO_verification");
            }
            return rowsAffected;
        } catch (Exception e) {
            logger.error("Error saving to NGO_verification: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving to NGO_verification: " + e.getMessage(), e);
        }
    }



    public Optional<NGO> findByUsername(String username) {
        String sql = "SELECT * FROM NGO WHERE username = ?";
        return jdbcTemplate.query(sql, ngoRowMapper, username).stream().findFirst();
    }

    public Optional<NGO> findByEmail(String email) {
        String sql = "SELECT * FROM NGO WHERE email = ?";
        return jdbcTemplate.query(sql, ngoRowMapper, email).stream().findFirst();
    }
}