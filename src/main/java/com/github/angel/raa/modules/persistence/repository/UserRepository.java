package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.modesl.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> { }
