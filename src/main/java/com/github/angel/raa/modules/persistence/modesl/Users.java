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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Image.class)
    @JoinTable(name = "users_images", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "image_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "image_id"}, name = "users_images_unique_constraint"))
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
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Post.class)
    @OrderBy("publishedAt DESC")
    @JoinTable(name = "users_posts", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "post_id"}, name = "users_posts_unique_constraint"))
    private Set<Post> posts;
}
