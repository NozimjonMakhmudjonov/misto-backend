package com.example.security3.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EntProperty extends BaseEntity{
    private String name;
    private String value;
}
