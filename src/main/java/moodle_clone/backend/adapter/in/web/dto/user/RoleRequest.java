package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotNull;
import moodle_clone.backend.domain.model.enums.user.Roles;

public record RoleRequest(
        @NotNull Roles role
) {}