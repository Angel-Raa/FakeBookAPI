package com.github.angel.raa.modules.persistence.modesl;


import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_USER(Arrays.asList(
            Permission.WRITE_MY_PROFILE,
            Permission.WRITE_BLOG_POSTS,
            Permission.READ_BLOG_POSTS,
            Permission.READ_MY_PROFILE

    )),
    ROLE_ADMIN(Arrays.asList(
            Permission.WRITE_MY_PROFILE,
            Permission.COMMENT_ON_POSTS,
            Permission.WRITE_BLOG_POSTS,
            Permission.READ_BLOG_POSTS,
            Permission.READ_MY_PROFILE,
            Permission.DELETE_BLOG_POSTS,
            Permission.DELETE_COMMENTS,
            Permission.CREATE_ONE_POST
    ));


    private List<Permission> permissionList;

    Role(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
