package com.springDataProject.springDataProject.controllers;

import com.springDataProject.springDataProject.entities.Officer;
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
        public Officer createOfficer(@RequestBody Officer officer){
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
        public Optional<Officer> updateOfficerRank(@RequestParam String email, @PathVariable Long id){
            return personRepository.updateEmail(id, email);
        }
        @DeleteMapping("/officer/{id}")
        public ResponseEntity deletePerson(@PathVariable Long id){
            boolean deleted = personRepository.delete(id);
            if(!deleted){
                return ResponseEntity.notFound()
                        .header("errorMsg", "Person id "+id+" doesn't exist")
                        .build();
            }else{
                return ResponseEntity.ok().build();
            }
        }

    }
