package com.example.demo.dto;

import com.example.demo.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
  private String name;
  private String identificationNumber;
  private String phone;
  private String nickname;
  private Integer age;
  private Location home;
  private Location company;
}
