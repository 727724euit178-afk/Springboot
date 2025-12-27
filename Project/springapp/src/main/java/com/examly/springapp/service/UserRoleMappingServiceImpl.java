package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Role;
import com.examly.springapp.model.UserAccount;
import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.repository.RoleRepo;
import com.examly.springapp.repository.UserAccountRepo;
import com.examly.springapp.repository.UserRoleMappingRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {

    @Autowired
    private UserRoleMappingRepo userRoleMappingRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private RoleRepo roleRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserRoleMapping saveUserRole(UserRoleMapping mapping) {
        UserAccount user = userAccountRepo.findById(mapping.getUser().getId()).orElse(null);
        Role role = roleRepo.findById(mapping.getRole().getId()).orElse(null);

        if (user == null || role == null) {
            return null;
        }

        mapping.setUser(user);
        mapping.setRole(role);
        return userRoleMappingRepo.save(mapping);
    }

    @Override
    public List<UserRoleMapping> getAllUserRoleMappings() {
        return userRoleMappingRepo.findAll();
    }

    @Override
    public UserRoleMapping getUserRoleMappingById(Long id) {
        return userRoleMappingRepo.findById(id).orElse(null);
    }

    @Override
    public UserRoleMapping updateUserRoleMapping(Long id, UserRoleMapping mapping) {
        UserRoleMapping existing = userRoleMappingRepo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setUser(
            userAccountRepo.findById(mapping.getUser().getId()).orElse(null)
        );
        existing.setRole(
            roleRepo.findById(mapping.getRole().getId()).orElse(null)
        );

        return userRoleMappingRepo.save(existing);
    }

    @Override
    public List<UserRoleMapping> getUserRoleMappingsByUserId(Long userId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRoleMapping> cq = cb.createQuery(UserRoleMapping.class);

        Root<UserRoleMapping> root = cq.from(UserRoleMapping.class);
        root.join("user", JoinType.INNER);

        cq.select(root)
          .where(cb.equal(root.get("user").get("id"), userId));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public UserRoleMapping getUserRoleMappingByUserIdAndRoleId(Long userId, Long roleId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRoleMapping> cq = cb.createQuery(UserRoleMapping.class);

        Root<UserRoleMapping> root = cq.from(UserRoleMapping.class);
        root.join("user", JoinType.INNER);
        root.join("role", JoinType.INNER);

        cq.select(root)
          .where(
              cb.and(
                  cb.equal(root.get("user").get("id"), userId),
                  cb.equal(root.get("role").get("id"), roleId)
              )
          );

        List<UserRoleMapping> result =
                entityManager.createQuery(cq).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}