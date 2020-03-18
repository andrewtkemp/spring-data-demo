package com.springDataProject.springDataProject.repositories;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOfficerDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertOfficer;

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    public Long count() {
        String countQuery = "select count(*) from officers";
        return jdbcTemplate.queryForObject(countQuery, Long.class);
    }

    public List<Officer> findAll() {
        String selectAllQuery = "select * from officers";
        return jdbcTemplate.query(selectAllQuery,
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        );
    }

    public Optional<Officer> findById(Long id) {
        String findByIdQuery = "select * from officers where id=?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdQuery,
                ((rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))),id)
        );
    }

    public Officer save(Officer officer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("officer_rank", officer.getRank());
        parameters.put("first_name", officer.getFirst());
        parameters.put("last_name", officer.getLast());

        long newId = insertOfficer.executeAndReturnKey(parameters).longValue();
        officer.setId(newId);

        return officer;
    }
    // found here https://stackoverflow.com/questions/18259906/spring-jdbctemplate-delete-syntax
    public boolean delete(Long id){
        String deleteQuery = "DELETE FROM officers WHERE id = ?";
        Object[] args = new Object[] {id};

        return jdbcTemplate.update(deleteQuery, args) == 1;
    }
}