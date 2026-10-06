package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotNull;

public record UpdateNameRequest(@NotNull String name) {
}
