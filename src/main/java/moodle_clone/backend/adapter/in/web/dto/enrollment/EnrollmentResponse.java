package moodle_clone.backend.adapter.in.web.dto.enrollment;

import moodle_clone.backend.application.readmodel.enrollment.EnrollmentReadModel;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentResponse(
        UUID id,
        UUID studentId,
        UUID courseId,
        EnrollmentStatus status,
        LocalDate enrolledAt,
        LocalDate completedAt,
        LocalDate cancelledAt
) {
    public static EnrollmentResponse fromReadModel(EnrollmentReadModel rm) {
        return new EnrollmentResponse(rm.id(), rm.studentId(), rm.courseId(), rm.status(),
                rm.enrolledAt(), rm.completedAt(), rm.cancelledAt());
    }
}