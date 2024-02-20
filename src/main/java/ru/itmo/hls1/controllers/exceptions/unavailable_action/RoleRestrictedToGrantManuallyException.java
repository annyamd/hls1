package ru.itmo.hls1.controllers.exceptions.unavailable_action;

import ru.itmo.hls1.model.entity.Role;

public class RoleRestrictedToGrantManuallyException extends UnavailableActionException {
    public RoleRestrictedToGrantManuallyException(Role role) {
        super("User need to create entity for getting role:" + role.name());
    }
}
