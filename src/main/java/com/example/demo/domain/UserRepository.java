package com.example.demo.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

    @Query("select u from User u where id = :id")
    Optional<User> findByUserId(@Param("id") Integer id);

    @Query("select u.name from User u where id = :id")
    Optional<String> findUserName(Integer id);

    @Query(nativeQuery = true)
    List<User> findUserNameRaw();
}
