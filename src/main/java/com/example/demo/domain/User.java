package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
import com.vroong.encrypt.stereotype.EncryptedKey;
import com.vroong.encrypt.stereotype.Hashed;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

  @Encrypted
  @Column(name = "name_enc")
  private String name;

  @Encrypted
  @Column(name = "age_enc")
  private String age;

  @Encrypted(properties = {"latLng.lat", "latLng.lng"})
  @Embedded
  @Convert(disableConversion = true, attributeName = "latLng.lat")
  @Convert(disableConversion = true, attributeName = "latLng.lng")
  @AttributeOverrides({
    @AttributeOverride(name = "address", column = @Column(name = "home_address")),
    @AttributeOverride(name = "latLng.lat", column = @Column(name = "home_lat_enc")),
    @AttributeOverride(name = "latLng.lng", column = @Column(name = "home_lng_enc"))
  })
  private Location home;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "address", column = @Column(name = "company_address")),
    @AttributeOverride(name = "latLng.lat", column = @Column(name = "company_lat")),
    @AttributeOverride(name = "latLng.lng", column = @Column(name = "company_lng"))
  })
  private Location company;

  @Hashed(origin = "name")
  @Column(name = "name_hashed")
  private String nameHashed;

  @EncryptedKey
  @Column(name = "enc_dek")
  private String encDEK;

  // @TODO Start of code for encryption migration

  @Column(name = "name")
  private String _origName;

  @Column(name = "age")
  private Integer _origAge;

  @Column(name = "home_lat")
  private Double _origHomeLat;

  @Column(name = "home_lng")
  private Double _origHomeLng;

  @Column(name = "identification_number")
  private String _origIdentificationNumber;

  @Column(name = "phone")
  private String _origPhone;

  private Location buildLocation(String address, LatLng latLng) {
    if (address == null && latLng == null) {
      return null;
    } else {
      return new Location(address, latLng);
    }
  }

  private LatLng buildLatLng(Double lat, Double lng) {
    if (lat == null && lng == null) {
      return null;
    } else {
      return new LatLng(lat == null ? null : lat.toString(), lng == null ? null : lng.toString());
    }
  }

  @PostLoad
  public void postLoad() {
    if (!Objects.equals(name, _origName)) name = _origName;
    if (!Objects.equals(getAge(), _origAge)) setAge(_origAge);

    // Setting embedded attribute
    Location _origHome =
        buildLocation(
            Optional.ofNullable(home).map(Location::getAddress).orElse(null),
            buildLatLng(_origHomeLat, _origHomeLng));
    if (!Objects.equals(home, _origHome)) home = _origHome;

    if (!Objects.equals(getIdentificationNumber(), _origIdentificationNumber))
      setIdentificationNumber(_origIdentificationNumber);
    if (!Objects.equals(getPhone(), _origPhone)) setPhone(_origPhone);
  }

  @PrePersist
  @PreUpdate
  public void prePersist() {
    _origName = name;
    _origAge = getAge();
    _origHomeLat = Optional.ofNullable(home).map(Location::getLatLng).map(LatLng::getLat).orElse(null);
    _origHomeLng = Optional.ofNullable(home).map(Location::getLatLng).map(LatLng::getLng).orElse(null);
    _origIdentificationNumber = getIdentificationNumber();
    _origPhone = getPhone();
  }

  // @TODO End of code for encryption migration

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
    this.age = age == null ? null : age.toString();
    this.home = home;
    this.company = company;
  }

  public Integer getAge() {
    return age == null ? null : Integer.parseInt(age);
  }

  public void setAge(Integer age) {
    this.age = age == null ? null : age.toString();
  }
}
