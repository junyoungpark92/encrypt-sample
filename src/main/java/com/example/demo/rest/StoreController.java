package com.example.demo.rest;

import com.example.demo.domain.Store;
import com.example.demo.domain.StoreRepository;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
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
@RequestMapping(path = "/stores")
public class StoreController {
  private StoreRepository storeRepository;

  public StoreController(StoreRepository storeRepository) {
    this.storeRepository = storeRepository;
  }

  @PostMapping("")
  public Store save(@RequestBody String name) {
    Store store = new Store(name);
    storeRepository.save(store);
    return store;
  }

  @GetMapping("{id}")
  public Store find(@PathVariable Integer id) {
    return storeRepository.findById(id).get();
  }
}
