package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Profile;
import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.ProfileRepo;
import com.examly.springapp.repository.UserAccountRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private UserAccountRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profile saveProfile(Profile profile) {
        if(profile.getUser() != null){
            Long userId = profile.getUser().getId();
            UserAccount user = userRepo.findById(userId).orElse(null);
            if(user != null){
                profile.setUser(user);
            }
        }
        return profileRepo.save(profile);
    }

    @Override
    public List<Profile> findAllProfiles() {
        return profileRepo.findAll();
    }

    @Override
    public Profile findById(Long profileId) {
        return profileRepo.findById(profileId).orElse(null);
    }

    @Override
    public Profile updateProfile(Long profileId, Profile newDetails) {
        Profile existing = profileRepo.findById(profileId).orElse(null);
        if(existing != null){
            existing.setFirstName(newDetails.getFirstName());
            existing.setLastName(newDetails.getLastName());
            existing.setAddress(newDetails.getAddress());
            existing.setDob(newDetails.getDob());
            existing.setGender(newDetails.getGender());

            if(newDetails.getUser() != null){
                Long userId = newDetails.getUser().getId();
                UserAccount user = userRepo.findById(userId).orElse(null);
                if(user != null){
                    existing.setUser(user);
                }
            }

            return profileRepo.save(existing);
        }
        return null;
    }


    @Override
    public List<Profile> getProfilesByFirstName(String firstName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);

        cq.select(root)
          .where(cb.like(root.get("firstName"), "%" + firstName + "%"));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Profile> getProfilesByFirstNameAndAddress(String firstName, String address) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> cq = cb.createQuery(Profile.class);
        Root<Profile> root = cq.from(Profile.class);

        cq.select(root)
          .where(cb.and(
              cb.like(root.get("firstName"), "%" + firstName + "%"),
              cb.like(root.get("address"), "%" + address + "%")
          ));

        return entityManager.createQuery(cq).getResultList();
    }
}
