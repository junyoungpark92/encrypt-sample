package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
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

  @Encrypted
  @Column(name = "identification_number_enc")
  private String identificationNumber;

  @Encrypted
  @Column(name = "phone_enc")
  private String phone;
}
