package moodle_clone.backend.application.port.in.user;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateDateBirthCommand(
        UUID userId,
        LocalDate dateBirth
) {}