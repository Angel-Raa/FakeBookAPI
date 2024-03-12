package com.github.angel.raa.modules.persistence.modesl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String photo;
    private String bio;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "update_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Post.class)
    @OrderBy("publishedAt DESC")
    @Column(name = "posts")
    private Set<Post> posts;
}
