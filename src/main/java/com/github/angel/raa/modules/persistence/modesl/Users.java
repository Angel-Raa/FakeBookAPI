package com.github.angel.raa.modules.persistence.modesl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",  uniqueConstraints = @UniqueConstraint(columnNames = "username"), schema = "public")
public class Users implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username", unique = true, length = 50)
    private String username;
    private String password;
    @Column(name = "email", length = 50)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) return Collections.emptyList();
        if(role.getPermissionList() == null) return Collections.emptyList();
        return role.getPermissionList()
                .stream().map(Enum::name)
                .map((SimpleGrantedAuthority::new))
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
