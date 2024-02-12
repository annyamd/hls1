package ru.itmo.hls1.controllers.exceptions.role;

import ru.itmo.hls1.model.entity.Role;

public class RoleAlreadyGrantedException extends RoleNotGrantedException{
    public RoleAlreadyGrantedException(long id, Role role) {
        super("User (id = " + id + ") already is " + role.name());
    }
}
