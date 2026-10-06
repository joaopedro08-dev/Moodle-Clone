package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import moodle_clone.backend.domain.model.enums.user.Roles;

public record CreateUserRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotNull Roles role
) {}