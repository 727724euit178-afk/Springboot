package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Role;
import com.examly.springapp.service.RoleService;


@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/roles")
    public ResponseEntity<Role> addRoles(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
    }


@GetMapping("/roles")
public ResponseEntity<List<Role>> findAllRoles() {
    return new ResponseEntity<>(roleService.findAllRoles(),HttpStatus.OK);
}


@GetMapping("/roles/{roleId}")
public ResponseEntity<Role> getById(@PathVariable Long roleId) {
    Role role = roleService.findById(roleId);
    if (role == null) {
        return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(role,HttpStatus.OK);
}


@GetMapping("/roles/page/{page}/{size}")
public ResponseEntity<Page<Role>> getRolesWithPagination(
        @PathVariable int page,
        @PathVariable int size) {

    Pageable pageable = PageRequest.of(page, size);
    return new ResponseEntity<>(roleService.getAllRoles(pageable),HttpStatus.OK);
}



   @PutMapping("/roles/{roleId}")
public ResponseEntity<Role> editRole(
        @PathVariable Long roleId,
        @RequestBody Role role) {

    Role updatedRole = roleService.editRole(roleId, role);

    if (updatedRole == null) {
        return ResponseEntity.notFound().build();
    }

    return new ResponseEntity<>(updatedRole, HttpStatus.OK);
}


    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable Long roleId) {
        boolean deleted = roleService.deleteRole(roleId);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
