package moodle_clone.backend.adapter.out.persistence.mapper.enrollment;

import moodle_clone.backend.adapter.out.persistence.entity.enrollment.EnrollmentJpaEntity;
import moodle_clone.backend.domain.model.enrollment.Enrollment;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;

public class EnrollmentMapper {

    public static EnrollmentJpaEntity toJpaEntity(Enrollment enrollment) {
        EnrollmentJpaEntity entity = new EnrollmentJpaEntity();
        entity.setId(enrollment.getId());
        entity.setCreatedAt(enrollment.getCreatedAt());
        entity.setUpdatedAt(enrollment.getUpdatedAt());
        entity.setStudentId(enrollment.getStudentId());
        entity.setCourseId(enrollment.getCourseId());
        entity.setStatus(enrollment.getStatus() != null ? enrollment.getStatus().name() : null);
        entity.setEnrolledAt(enrollment.getEnrolledAt());
        entity.setCompletedAt(enrollment.getCompletedAt());
        entity.setCancelledAt(enrollment.getCancelledAt());

        return entity;
    }

    public static Enrollment toDomain(EnrollmentJpaEntity enrollment) {
        EnrollmentStatus status = enrollment.getStatus() != null ? EnrollmentStatus.valueOf(enrollment.getStatus())
                : null;

        return Enrollment.reconstruct(enrollment.getId(), enrollment.getCreatedAt(),
                enrollment.getUpdatedAt(), enrollment.getStudentId(), enrollment.getCourseId(),
                status, enrollment.getEnrolledAt(), enrollment.getCompletedAt(), enrollment.getCancelledAt());
    }
}