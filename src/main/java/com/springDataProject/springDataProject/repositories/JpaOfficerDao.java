package com.springDataProject.springDataProject.repositories;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOfficerDao extends OfficerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Officer save(Officer officer);

    @Override
    public Optional<Officer> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Officer.class, id));
    }

    @Override
    public void delete(Officer officer) {
        entityManager.remove(officer);

    }

    @Override
    public boolean existsById(Integer id) {
        Long count = entityManager.createQuery(
                "select count(o.id) from Officer o where o.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

}