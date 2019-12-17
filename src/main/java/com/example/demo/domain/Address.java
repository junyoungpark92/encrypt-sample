package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
  @Column(name = "bunji")
  private String bunji;

  public String getBunji() {
    return bunji;
  }

  public void setBunji(String bunji) {
    this.bunji = bunji;
  }
}
