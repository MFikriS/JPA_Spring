package com.example.demo.models;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "fullname")
    private String Fullname;

    @Column(name = "email")
    private String Email;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Birthdate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

    public void setId(Integer id){
        Id = id;
    }

    public Integer getId(){
        return Id;
    }

    public void setFullname(String fullname){
        Fullname = fullname;
    }

    public String getFullname(){
        return Fullname;
    }

    public void setEmail(String email){
        Email = email;
    }

    public String getEmail(){
        return Email;
    }

    public void setBirthdate(LocalDate birthdate){
        Birthdate = birthdate;
    }

    public LocalDate getBirthdate(){
        return Birthdate;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}
