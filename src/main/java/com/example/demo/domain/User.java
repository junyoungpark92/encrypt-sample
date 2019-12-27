package com.example.demo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User extends IdentifiableUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "address", column = @Column(name = "home_address")),
    @AttributeOverride(name = "latLng.lat", column = @Column(name = "home_lat")),
    @AttributeOverride(name = "latLng.lng", column = @Column(name = "home_lng"))
  })
  private Location home;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "address", column = @Column(name = "company_address")),
    @AttributeOverride(name = "latLng.lat", column = @Column(name = "company_lat")),
    @AttributeOverride(name = "latLng.lng", column = @Column(name = "company_lng"))
  })
  private Location company;

  public User(
      String identificationNumber,
      String phone,
      String nickname,
      String name,
      Integer age,
      Location home,
      Location company) {
    super(identificationNumber, phone);
    this.nickname = nickname;
    this.name = name;
    this.age = age;
    this.home = home;
    this.company = company;
  }
}
