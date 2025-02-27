package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "staffs")
@Getter
@Setter
public class Staff extends User{
}
