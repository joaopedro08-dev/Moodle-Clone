package moodle_clone.backend.application.port.in.user;

import java.util.UUID;

public record UpdateNameCommand (UUID userId, String name){}