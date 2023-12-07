package com.example.security3.entity;

import com.example.security3.enums.TokenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "update tokens set deleted = true where id = ?", check = ResultCheckStyle.COUNT)
public class EntToken extends BaseEntity {

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private EntUser user;

}
