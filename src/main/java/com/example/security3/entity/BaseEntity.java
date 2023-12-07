package com.example.security3.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false, columnDefinition = "timestamp without time zone default now()")
    private LocalDateTime createdDate = LocalDateTime.now(ZoneOffset.systemDefault());

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime lastModifiedDate = LocalDateTime.now(ZoneOffset.systemDefault());

    @LastModifiedBy
    @Column()
    private Long lastModifiedBy;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;
}
