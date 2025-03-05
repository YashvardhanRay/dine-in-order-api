package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table( name = "admins")
@Setter
@Getter
public class Admin extends User{

    @OneToMany
    private List<Restaurant> restaurant;
}
