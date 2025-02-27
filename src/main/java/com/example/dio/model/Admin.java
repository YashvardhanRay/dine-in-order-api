package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "admins")
@Setter
@Getter
public class Admin extends User{
}
