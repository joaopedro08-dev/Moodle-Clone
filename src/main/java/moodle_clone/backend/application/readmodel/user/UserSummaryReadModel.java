package moodle_clone.backend.application.readmodel.user;

import java.util.UUID;

public record UserSummaryReadModel(
        UUID id,
        String name,
        String email,
        boolean isActive
) {}