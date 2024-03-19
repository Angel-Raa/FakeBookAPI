package com.github.angel.raa.modules.persistence.modesl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Image.class)
    @JoinColumn(name = "image_id")
    private Image image;
    private String bio;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "update_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private Role role;
}
