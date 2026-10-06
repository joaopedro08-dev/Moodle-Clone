package moodle_clone.backend.application.port.in.user;

import moodle_clone.backend.domain.model.enums.user.Nationality;

import java.util.UUID;

public record UpdateNationalityCommand(
        UUID userId,
        Nationality nationality
) {}