package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Profile;
import com.examly.springapp.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // CREATE
    @PostMapping
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
        Profile savedProfile = profileService.saveProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.findAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    // GET BY ID
    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getById(@PathVariable Long profileId) {
        Profile profile = profileService.findById(profileId);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }

    // UPDATE
    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long profileId, @RequestBody Profile profile) {
        Profile updated = profileService.updateProfile(profileId, profile);
        return ResponseEntity.ok(updated);
    }

    // GET BY FIRST NAME (list)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Profile>> getProfilesByName(@PathVariable String name) {
        List<Profile> profiles = profileService.getProfilesByFirstName(name);
        if (profiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profiles);
    }

    // GET BY FIRST NAME AND ADDRESS
    @GetMapping("/search/{name}/{address}")
    public ResponseEntity<List<Profile>> getProfilesByNameAndAddress(
            @PathVariable String name,
            @PathVariable String address) {

        List<Profile> profiles = profileService.getProfilesByFirstNameAndAddress(name, address);

        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}
