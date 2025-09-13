package com.springapp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
