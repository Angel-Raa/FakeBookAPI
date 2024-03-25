package com.github.angel.raa.modules.persistence.modesl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tokens", schema = "public")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id", nullable = false)
    private Long tokenId;
    @Column(name = "token", nullable = false, length = 300)
    private String token;
    @Column(name = "is_valid", nullable = false)
    private boolean isValid;
    @Column(name = "expiration_date",  nullable = false)
    private Date expiration;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;
}
