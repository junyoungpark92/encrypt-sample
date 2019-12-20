package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
import com.vroong.encrypt.stereotype.EncryptedKey;

import javax.persistence.*;

@Entity
@SecondaryTable(
    name = "user_enc_key",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id")
)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Encrypted
    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "secret_number")
    private String secretNumber;

    @Encrypted(properties = {"address1"})
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "address1", column = @Column(name = "address1")),
        @AttributeOverride(name = "address2", column = @Column(name = "address2"))
    })
    private Address address;

    @EncryptedKey
    @Column(name = "enc_DEK", table = "user_enc_key")
    private String encDEK;

    protected User() { }

    public User(String name, String nickname, String secretNumber, Address address) {
        this.name = name;
        this.nickname = nickname;
        this.secretNumber = secretNumber;
        this.address = address;
    }

    public User(Integer id, String name, String nickname, String secretNumber, String encDEK, Address address) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.secretNumber = secretNumber;
        this.encDEK = encDEK;
        this.address = address;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(String secretNumber) {
        this.secretNumber = secretNumber;
    }

    public String getEncDEK() {
        return encDEK;
    }

    public void setEncDEK(String encDEK) {
        this.encDEK = encDEK;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
