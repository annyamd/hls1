package ru.itmo.hls1.controllers.exceptions.role;

import ru.itmo.hls1.model.entity.Role;

public class RoleRestrictedToGrantManuallyException extends RoleNotGrantedException{
    public RoleRestrictedToGrantManuallyException(Role role) {
        super("User need to create entity for getting role:" + role.name());
    }
}
