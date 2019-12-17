package com.example.demo.domain;

import com.vroong.encrypt.stereotype.Encrypted;
import com.vroong.encrypt.stereotype.EncryptedKey;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Audited
    @Encrypted
    @Column(name = "name")
    private String name;

    @Audited
    @Column(name = "nickname")
    private String nickname;

    @Audited
    @Encrypted(decryptable = false)
    @Column(name = "secret_numver")
    private String secretNumber;

    @Audited
    @EncryptedKey
    @Column(name = "enc_DEK")
    private String encDEK;

    @Embedded
    @AttributeOverride(name = "bunji", column = @Column(name = "modified_bunji"))
    private Address address;

    protected User() { }

    public User(String name, String nickname, String secretNumber) {
        this.name = name;
        this.nickname = nickname;
        this.secretNumber = secretNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
