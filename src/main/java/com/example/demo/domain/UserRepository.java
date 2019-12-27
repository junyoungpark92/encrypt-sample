package com.example.demo.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.id = :id")
    Optional<User> findByUserId(@Param("id") Integer id);

    List<User> findByName(String name);
}
