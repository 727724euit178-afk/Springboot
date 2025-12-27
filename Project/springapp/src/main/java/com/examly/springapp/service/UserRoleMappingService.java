package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.UserRoleMapping;

public interface UserRoleMappingService {

    UserRoleMapping saveUserRole(UserRoleMapping mapping);

    List<UserRoleMapping> getAllUserRoleMappings();

    UserRoleMapping getUserRoleMappingById(Long id);

    UserRoleMapping updateUserRoleMapping(Long id, UserRoleMapping mapping);

    List<UserRoleMapping> getUserRoleMappingsByUserId(Long userId);

    UserRoleMapping getUserRoleMappingByUserIdAndRoleId(Long userId, Long roleId);
}
