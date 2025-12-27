package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long>{
    
}
