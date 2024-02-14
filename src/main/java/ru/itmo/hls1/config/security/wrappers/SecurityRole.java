package ru.itmo.hls1.config.security.wrappers;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import ru.itmo.hls1.model.entity.Role;


@RequiredArgsConstructor
public class SecurityRole implements GrantedAuthority{
    final Role role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}