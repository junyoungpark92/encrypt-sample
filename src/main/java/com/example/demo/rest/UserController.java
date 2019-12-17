package com.example.demo.rest;

import com.example.demo.domain.Address;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.vroong.encrypt.helper.EncryptionHelper;
import java.util.List;
import org.springframework.data.history.Revisions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/users")
public class UserController {
    private UserRepository userRepository;
    private EncryptionHelper encryptionHelper;

    public UserController(UserRepository userRepository, EncryptionHelper encryptionHelper) {
        this.userRepository = userRepository;
        this.encryptionHelper = encryptionHelper;
    }

    @PostMapping("")
    public void save(@RequestBody String name) {
        User user = new User(name, "joont", "000000-0000000");
        Address address = new Address();
        address.setBunji("bunji~~~~");
        user.setAddress(address);
        userRepository.save(user);
    }

    @GetMapping("/{id}")
    @Transactional
    public User find(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody Body body) {
        User user = userRepository.findById(id).get();
        user.setName(body.getName());
        user.setSecretNumber(body.getSecurityNumber());
    }

    @GetMapping("")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/search")
    public List<User> findByName(@RequestParam String name) {
        return userRepository.findByName(encryptionHelper.getEncryptedData(name, "user"));
    }

    @GetMapping("/{id}/revisions")
    public Revisions<Integer, User> findWithRevision(@PathVariable Long id) {
        return userRepository.findRevisions(id);
    }

    class Body {
        String name;
        String securityNumber;

        public Body(String name, String securityNumber) {
            this.name = name;
            this.securityNumber = securityNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSecurityNumber() {
            return securityNumber;
        }

        public void setSecurityNumber(String securityNumber) {
            this.securityNumber = securityNumber;
        }
    }
}
