package moodle_clone.backend.application.port.in.enrollment;

import java.util.UUID;

public record CreateEnrollmentCommand(UUID studentId, UUID courseId) {}