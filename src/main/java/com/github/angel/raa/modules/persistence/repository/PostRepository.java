package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.modesl.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
