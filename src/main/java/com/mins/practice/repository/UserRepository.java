package com.mins.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mins.practice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
