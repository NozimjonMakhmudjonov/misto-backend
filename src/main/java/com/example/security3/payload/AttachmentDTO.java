package com.example.security3.payload;

import lombok.Data;

@Data
public class AttachmentDTO {
    private Integer id;
    private String originalName;
    private long size;
    private String contentType;
    private String name;
    private Long userId;
}
