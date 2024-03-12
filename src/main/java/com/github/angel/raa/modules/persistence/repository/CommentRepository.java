package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.modesl.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}



