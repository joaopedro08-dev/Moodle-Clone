package moodle_clone.backend.application.port.in.course;

import java.util.UUID;

public record UpdateDescriptionCommand(UUID courseId, String description) {}