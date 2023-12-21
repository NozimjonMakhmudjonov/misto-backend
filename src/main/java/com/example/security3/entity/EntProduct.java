package com.example.security3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class EntProduct extends BaseEntity {
    private String name;
    private String description;
    private double price;
    @ManyToMany
    private List<EntAttachment> attachments;
    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private EntCategory category;
    @OneToMany
    private List<EntProperty> properties;


}
