package ru.itmo.hls1.controllers.exceptions.already_applied;

import ru.itmo.hls1.model.entity.Role;

public class RoleAlreadyGrantedException extends AlreadyAppliedException {
    public RoleAlreadyGrantedException(long id, Role role) {
        super("User (id = " + id + ") already is " + role.name());
    }
}
