package com.github.angel.raa.modules.persistence.repository;

import com.github.angel.raa.modules.persistence.modesl.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
