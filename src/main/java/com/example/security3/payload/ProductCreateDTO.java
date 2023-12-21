package com.example.security3.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDTO {
    private String name;
    private String description;
    private double price;
    private List<String> attachments;
    private Long categoryId;
    private List<Long> properties;
}
