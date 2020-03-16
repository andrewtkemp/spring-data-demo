package com.springDataProject.springDataProject.repositories;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    public List<Officer> findAll() {
        return jdbcTemplate.query("select * from officers",
                (rs, rowNum) -> new Officer(rs.getLong("id"),
                        Rank.valueOf(rs.getString("officer_rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        );
    }

    public boolean existsById(long id) {
        boolean found = false;
        try {
            found = jdbcTemplate.queryForObject(
                    "select 1 from officers where id = ?", Boolean.class, id);
        }catch (EmptyResultDataAccessException e){
            //ignore
        }
        return found;
    }

    public Optional<Officer> findById(Long id) {
        if(!existsById(id)) return Optional.empty();
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from officers where id=?",
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
}