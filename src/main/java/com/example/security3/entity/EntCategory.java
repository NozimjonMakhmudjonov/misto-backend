package com.example.security3.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EntCategory extends BaseEntity{
    private String name;
}
