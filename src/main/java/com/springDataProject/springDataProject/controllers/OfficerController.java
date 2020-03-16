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
public class OfficerController {


    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcOfficerDao jdbcOfficerDao;
    JpaOfficerDao jpaOfficerDao;


    public OfficerController(JdbcTemplate jdbcTemplate, JpaOfficerDao jpaOfficerDao){
        jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
        this.jpaOfficerDao = jpaOfficerDao;
    }


    //CREATE


    @PostMapping("/officers")
    public Officer JDBCPostOfficer(@RequestBody Officer officer){
        return jdbcOfficerDao.save(officer);
    }


    //READ


    @GetMapping("/officers")
    public List<Officer> JDBCGetAllOfficers() {
        List<Officer> officerList = jdbcOfficerDao.findAllOfficers();
        return officerList;
    }

    @GetMapping("/officers/{id}")
    public Optional<Object> JDBCGetOfficerByID(@PathVariable Long id){
        Optional<Object> officer = jdbcOfficerDao.findOfficerById(id);
        return officer;
    }


    //UPDATE


    @PatchMapping("/officers/{id}/{rank}")
    public Officer JPAPatchOfficerRank(@PathVariable Long id, @PathVariable Rank rank){
        return jpaOfficerDao.updateRankByID(id, rank);
    }


    // DELETE


    @DeleteMapping("/officers/{id}")
    public void JBDCDeleteOfficerByID( @PathVariable Long id){
        jdbcOfficerDao.delete(id);
    }

}