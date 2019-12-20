package com.example.demo.rest;

import com.example.demo.domain.Address;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.dto.UserDto;
import com.vroong.encrypt.helper.EncryptionHelper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private UserRepository userRepository;
    private EncryptionHelper exactMatchHelper;

    public UserController(UserRepository userRepository, EncryptionHelper exactMatchHelper) {
        this.userRepository = userRepository;
        this.exactMatchHelper = exactMatchHelper;
    }

    @PostMapping("")
    public void save(@RequestBody UserDto userDto) {
        User user = new User(userDto.getName(), "joont", "000000-0000000", new Address(userDto.getAddress1(), userDto.getAddress2()));
        userRepository.save(user);
    }

    @GetMapping("{id}")
    public User find(@PathVariable Integer id) {
        return userRepository.findByUserId(id).get();
    }

    @GetMapping("{id}/name")
    public String findName(@PathVariable Integer id) {
        String name = userRepository.findUserName(id).get();
        return name;
    }

    @GetMapping("v2")
    public List<User> findUsersV2() {
        List<User> users = userRepository.findUserNameRaw();
        return users;
    }

    @GetMapping("")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        User user = users.get(0);
//        user.setName(user.getName() + "1");
        userRepository.save(user);
        return users;
    }

    @GetMapping("search")
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findByName(exactMatchHelper.getEncryptedData(name, "user"));
    }
}
