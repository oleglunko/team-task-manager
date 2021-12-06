package ru.oleglunko.taskmanager.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    MANAGER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
