package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class IdentifiableUser {

  @Column(name = "identification_number")
  private String identificationNumber;

  @Column(name = "phone")
  private String phone;
}
