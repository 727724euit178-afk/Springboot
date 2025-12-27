package com.examly.springapp.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.Role;


public interface RoleService {

    Role saveRole(Role role);

    Role findById(Long roleId);

    Role editRole(Long roleId, Role role);

    boolean deleteRole(Long roleId);

    Page<Role> getAllRoles(Pageable pageable);

    List<Role> findAllRoles();
}
