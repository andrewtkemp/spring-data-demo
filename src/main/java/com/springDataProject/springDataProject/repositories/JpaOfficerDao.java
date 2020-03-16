package com.springDataProject.springDataProject.repositories;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaOfficerDao extends JpaRepository<Officer, Long> {
    Optional<Officer> update(Long id);

    void delete(Long id);
}