package com.github.angel.raa.modules.persistence.modesl;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@Getter
public enum Role {
    ROLE_USER(List.of(Permission.READ_ONLY)),

    ROLE_ADMIN(Arrays.asList(
            Permission.READ_ONLY,
            Permission.CREATE_COMMENTS,
            Permission.UPDATE_COMMENTS,
            Permission.DELETE_COMMENTS,
            Permission.CREATE_POST,
            Permission.UPDATE_POST,
            Permission.DELETE_POSTS
    ));


    private final List<Permission> permission;
    Role(List<Permission> permission) {
        this.permission = permission;
    }

}
