package moodle_clone.backend.application.port.in.user;

import moodle_clone.backend.domain.model.enums.user.Roles;

public record CreateUserCommand(
        String name,
        String email,
        Roles role
) {}
