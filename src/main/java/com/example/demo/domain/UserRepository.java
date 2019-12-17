package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Integer> {
    List<User> findByName(String name);
}
