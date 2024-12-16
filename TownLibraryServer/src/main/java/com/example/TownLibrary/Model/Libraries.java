package com.example.TownLibrary.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "libraries")
public class Libraries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LibraryID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}