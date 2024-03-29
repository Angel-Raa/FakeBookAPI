package com.github.angel.raa.modules.persistence.modesl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "image", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    @Serial
    private static final long serialVersionUID = 184179470754667710L;
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name_image")
    private String name;
    @Column(name = "url_image")
    private String url;
}
