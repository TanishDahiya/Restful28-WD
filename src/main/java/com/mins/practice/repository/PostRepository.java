package com.mins.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mins.practice.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
