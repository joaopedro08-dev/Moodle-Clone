package moodle_clone.backend.application.readmodel.enrollment;

import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentReadModel(
        UUID id,
        UUID studentId,
        UUID courseId,
        EnrollmentStatus status,
        LocalDate enrolledAt,
        LocalDate completedAt,
        LocalDate cancelledAt
) {}