package com.springDataProject.springDataProject.controllers;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import com.springDataProject.springDataProject.repositories.JpaOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OfficerController {
        @Autowired
        JpaOfficerDao jpaOfficerDao;
        @PostMapping("/officer")
        public Officer createOfficer(@RequestBody Officer officer) {
            return jpaOfficerDao.save(officer);
        }
        @GetMapping("/officer")
        public List<Officer> getOfficer(){
            return jpaOfficerDao.findAll();
        }
        @GetMapping("/officer/{id}")
        public Optional<Officer> getOfficerById(@PathVariable Long id){
            return  jpaOfficerDao.findById(id);
        }
        @PatchMapping("/officer/{id}")
        public Optional<Officer> updateOfficer(@PathVariable Long id){
            return jpaOfficerDao.update(id);
        }
        @DeleteMapping("/officer/{id}")
        public void deletePerson(@PathVariable Long id){
            jpaOfficerDao.delete(id);
        }

    }
