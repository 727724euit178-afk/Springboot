package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Role;
import com.examly.springapp.repository.RoleRepo;

@Service
public class RoleServiceImpl  implements RoleService{
      @Autowired
  RoleRepo roleRepo;
 //save
   public Role saveRole(Role role){
    return roleRepo.save(role);
   }
  
   //findAll
   public List<Role> findAllRoles(){
    return roleRepo.findAll();
   }
   
   //findById
   public Role findById(Long roleId){
    return roleRepo.findById(roleId).orElse(null);
   }
   
   //update
   public Role editRole(Long roleId, Role newDetails){
  
    Role existingDetails = roleRepo.findById(roleId).orElse(null);
    if(existingDetails != null){
        existingDetails.setRoleName(newDetails.getRoleName());
        return roleRepo.save(existingDetails);
    }
    else{
        return null;
    }
}

 public boolean deleteRole(Long roleId){
    if(!roleRepo.existsById(roleId)){
        return false;
    }
    roleRepo.deleteById(roleId);
    return true;

 }

    @Override
public Page<Role> getAllRoles(Pageable pageable) {
    return roleRepo.findAll(pageable);
}

   


   
      




 

 

 

}
