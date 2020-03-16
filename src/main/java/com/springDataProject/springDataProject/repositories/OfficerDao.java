package com.springDataProject.springDataProject.repositories;

import  com.springDataProject.springDataProject.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerDao extends JpaRepository<Officer, Integer> {
}