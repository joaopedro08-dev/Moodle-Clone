package moodle_clone.backend.adapter.in.web.dto.enrollment;

import moodle_clone.backend.application.readmodel.enrollment.EnrollmentSummaryReadModel;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;

import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentSummaryResponse(
        UUID id,
        UUID studentId,
        UUID courseId,
        EnrollmentStatus status,
        LocalDate enrolledAt
) {
    public static EnrollmentSummaryResponse fromReadModel(EnrollmentSummaryReadModel rm) {
        return new EnrollmentSummaryResponse(rm.id(), rm.studentId(), rm.courseId(), rm.status(), rm.enrolledAt());
    }
}