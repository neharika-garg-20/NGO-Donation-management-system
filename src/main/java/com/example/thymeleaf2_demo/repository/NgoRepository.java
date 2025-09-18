package com.example.thymeleaf2_demo.repository;

import com.example.thymeleaf2_demo.model.ngo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NgoRepository {
    private final JdbcTemplate jdbcTemplate;

    public NgoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ngo> findVerifiedNgos(String search, String city, String item) {
        StringBuilder sql = new StringBuilder(
            "SELECT n.ngo_id, n.organization_name, n.city, n.accepted_item_types, n.email, n.website_url " +
            "FROM NGO n " +
            "JOIN NGO_Verification nv ON n.ngo_id = nv.ngo_id " +
            "WHERE nv.status = 'Verified'"
        );

        List<Object> params = new java.util.ArrayList<>();

        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (LOWER(n.organization_name) LIKE ? OR LOWER(n.city) LIKE ? OR n.accepted_item_types LIKE ?)");
            String searchPattern = "%" + search.trim().toLowerCase() + "%";
            params.add(searchPattern);
            params.add(searchPattern);
            params.add(searchPattern);
        }

        if (city != null && !city.equals("all")) {
            sql.append(" AND n.city = ?");
            params.add(city);
        }

        if (item != null && !item.equals("all")) {
            sql.append(" AND FIND_IN_SET(?, n.accepted_item_types)");
            params.add(item);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
            String itemTypes = rs.getString("accepted_item_types");
            List<String> items = itemTypes != null ? Arrays.asList(itemTypes.split(",")) : List.of();
            return new ngo(
                rs.getInt("ngo_id"),
                rs.getString("organization_name"),
                rs.getString("city"),
                items,
                rs.getString("email"),
                rs.getString("website_url")
            );
        });
    }

    public List<String> findAllCities() {
        String sql = "SELECT DISTINCT n.city " +
                     "FROM NGO n " +
                     "JOIN NGO_Verification nv ON n.ngo_id = nv.ngo_id " +
                     "WHERE nv.status = 'Verified' AND n.city IS NOT NULL " +
                     "ORDER BY n.city";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("city"));
    }

    public List<String> findAllItemTypes() {
        String sql = "SELECT DISTINCT n.accepted_item_types " +
                     "FROM NGO n " +
                     "JOIN NGO_Verification nv ON n.ngo_id = nv.ngo_id " +
                     "WHERE nv.status = 'Verified'";
        List<String> itemSets = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("accepted_item_types"));
        return itemSets.stream()
            .flatMap(set -> Arrays.stream(set.split(",")))
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
}