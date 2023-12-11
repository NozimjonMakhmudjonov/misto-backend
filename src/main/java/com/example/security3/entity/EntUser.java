package com.example.security3.entity;

import com.example.security3.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "update users set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Table(name = "users")
public class EntUser extends BaseEntity {
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(orphanRemoval = true)
    private EntAttachment attachment;

}
