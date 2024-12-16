package com.example.TownLibrary.Model;

import jakarta.persistence.*;
import org.apache.tomcat.jni.Library;

import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReaderID")
    private Long readerID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "RegistrationDate")
    private LocalDate registrationDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "Role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "LibraryID")
    private Libraries library;

    public Long getReaderID() {
        return readerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Libraries getLibrary() {
        return library;
    }

    public void setLibrary(Libraries library) {
        this.library = library;
    }
}