package com.springDataProject.springDataProject.controllers;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import com.springDataProject.springDataProject.repositories.JpaOfficerDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/officers")
public class OfficerController {
    private JpaOfficerDao jpaOfficerDao;
    public OfficerController(JpaOfficerDao jpaOfficerDao){
        this.jpaOfficerDao = jpaOfficerDao;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Officer saveOfficer(@RequestBody Officer officer){
        return jpaOfficerDao.save(officer);
    }
    @GetMapping
    public List<Officer> getAllOfficers() {
        List<Officer> officerList = jpaOfficerDao.findAll();
        return officerList;
    }
    @GetMapping("{id}")
    public Optional<Officer> getOfficerById(@PathVariable Long id){
        Optional<Officer> officer = jpaOfficerDao.findById(id);
        return officer;
    }
    @PutMapping("{id}/{rank}")
    public Officer updateOfficer(@PathVariable Long id, @PathVariable Rank rank){
        // Find Officer
        Optional<Officer> officerToUpdate = jpaOfficerDao.findById(id);
        // Get officer to update
        Officer officer = officerToUpdate.get();
        // Rank setter
        officer.setRank(rank);
        // return saved officer query
        return jpaOfficerDao.save(officer);
    }
    @DeleteMapping("{id}")
    public void deleteOfficer( @PathVariable Long id){
       jpaOfficerDao.deleteById(id);
    }

}




