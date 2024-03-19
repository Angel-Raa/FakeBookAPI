package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.modesl.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);
    Optional<Token> findByToken(String token);
}
