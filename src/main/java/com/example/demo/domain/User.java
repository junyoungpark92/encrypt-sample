package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
import com.vroong.encrypt.stereotype.EncryptedKey;

import javax.persistence.*;

@Entity
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

    @Encrypted(decryptable = false)
    @Column(name = "secret_numver")
    private String secretNumber;

    @EncryptedKey
    @Column(name = "enc_DEK")
    private String encDEK;

    protected User() { }

    public User(String name, String nickname, String secretNumber) {
        this.name = name;
        this.nickname = nickname;
        this.secretNumber = secretNumber;
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
}
