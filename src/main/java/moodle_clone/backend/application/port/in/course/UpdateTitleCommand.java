package moodle_clone.backend.application.port.in.course;

import java.util.UUID;

public record UpdateTitleCommand(UUID courseId, String title) {}