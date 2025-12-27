package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Profile;

@Repository
public interface  ProfileRepo  extends JpaRepository<Profile,Long>{

}
