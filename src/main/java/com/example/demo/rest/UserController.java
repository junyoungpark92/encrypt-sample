package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.vroong.encrypt.helper.ExactMatchHelper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/users")
public class UserController {
    private UserRepository userRepository;
    private ExactMatchHelper exactMatchHelper;

    public UserController(UserRepository userRepository, ExactMatchHelper exactMatchHelper) {
        this.userRepository = userRepository;
        this.exactMatchHelper = exactMatchHelper;
    }

    @PostMapping("")
    public void save(@RequestBody String name) {
        User user = new User(name, "joont", "000000-0000000");
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User find(@PathVariable Integer id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/search")
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findByName(exactMatchHelper.getEncryptedData(name, "user"));
    }
}
