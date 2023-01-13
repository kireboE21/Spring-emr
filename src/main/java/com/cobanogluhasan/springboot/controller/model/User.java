package com.cobanogluhasan.springboot.controller.model;

import com.cobanogluhasan.springboot.Sha256Algorithm;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name="users")
public class User {
    //generate unique id
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;
    private long tckn;
    private String email;
    private String password;
    private long gsm;
    private String address;

    public User() {

    }

    public User(long tckn, String email, String password, long gsm, String address) {
        this.tckn = tckn;
        this.email = email;
        this.password = password;
        this.gsm = gsm;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTckn() {
        return tckn;
    }

    public void setTckn(long tckn) {
        this.tckn = tckn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = Sha256Algorithm.sha256(password);
    }

    public long getGsm() {
        return gsm;
    }

    public void setGsm(long gsm) {
        this.gsm = gsm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
