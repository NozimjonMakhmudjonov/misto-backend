package com.example.security3.payload;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<AttachmentDTO> attachments;
    private Long categoryId;
    private List<PropertyDTO> properties;
}
