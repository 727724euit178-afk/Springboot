package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Profile;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    List<Profile> findAllProfiles();

    Profile findById(Long id);

    Profile updateProfile(Long id, Profile profile);

    List<Profile> getProfilesByFirstName(String firstName);

    List<Profile> getProfilesByFirstNameAndAddress(String firstName, String address);
}
