package moodle_clone.backend.application.port.in.user;

import java.util.UUID;

public record UpdatePhoneCommand(
        UUID userId,
        String phoneNumber
) {}