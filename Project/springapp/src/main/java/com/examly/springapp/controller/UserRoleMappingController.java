package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.service.UserRoleMappingService;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {

    @Autowired
    private UserRoleMappingService service;

    @PostMapping
    public ResponseEntity<UserRoleMapping> create(@RequestBody UserRoleMapping mapping) {
        UserRoleMapping saved = service.saveUserRole(mapping);
        if (saved == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserRoleMapping>> getAll() {
        return new ResponseEntity<>(service.getAllUserRoleMappings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleMapping> getById(@PathVariable Long id) {
        UserRoleMapping mapping = service.getUserRoleMappingById(id);
        return mapping == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(mapping, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleMapping> update(
            @PathVariable Long id,
            @RequestBody UserRoleMapping mapping) {

        UserRoleMapping updated = service.updateUserRoleMapping(id, mapping);
        return updated == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(updated, HttpStatus.OK);
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleMapping>> getByUserId(@PathVariable Long userId) {
        List<UserRoleMapping> list = service.getUserRoleMappingsByUserId(userId);
        return list.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRoleMapping> getByUserAndRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        UserRoleMapping mapping =
                service.getUserRoleMappingByUserIdAndRoleId(userId, roleId);

        return mapping == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(mapping, HttpStatus.OK);
    }
}