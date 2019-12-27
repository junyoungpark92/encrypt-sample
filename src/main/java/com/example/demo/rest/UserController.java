package com.example.demo.rest;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.dto.UserDto;
import com.vroong.encrypt.helper.EncryptionHelper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  private final UserRepository userRepository;
  private final EncryptionHelper exactMatchHelper;

  public UserController(UserRepository userRepository, EncryptionHelper exactMatchHelper) {
    this.userRepository = userRepository;
    this.exactMatchHelper = exactMatchHelper;
  }

  @PostMapping("")
  public void save(@RequestBody UserDto userDto) {
    User user =
        new User(
            userDto.getIdentificationNumber(),
            userDto.getPhone(),
            userDto.getNickname(),
            userDto.getName(),
            userDto.getAge(),
            userDto.getHome(),
            userDto.getCompany());
    userRepository.save(user);
  }

  @GetMapping("{id}")
  public User find(@PathVariable Integer id) {
    return userRepository.findByUserId(id).get();
  }

  @PostMapping("{id}/refresh")
  public User findAndSave(@PathVariable Integer id) {
    User user = userRepository.findById(id).get();
    userRepository.save(user);
    return user;
  }

  @GetMapping("search")
  public List<User> findByName(@RequestParam String name) {
    return userRepository.findByName(exactMatchHelper.getEncryptedData(name, "user"));
  }
}
