package moodle_clone.backend.adapter.out.persistence.adapter.enrollment;

import moodle_clone.backend.adapter.out.persistence.entity.enrollment.EnrollmentJpaEntity;
import moodle_clone.backend.adapter.out.persistence.repository.enrollment.EnrollmentJpaRepository;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentQueryPort;
import moodle_clone.backend.application.readmodel.enrollment.EnrollmentReadModel;
import moodle_clone.backend.application.readmodel.enrollment.EnrollmentSummaryReadModel;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EnrollmentQueryAdapter implements EnrollmentQueryPort {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentQueryAdapter(EnrollmentJpaRepository enrollmentJpaRepository) {
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @Override
    public Optional<EnrollmentReadModel> findReadModelById(UUID id) {
        return enrollmentJpaRepository.findById(id).map(this::toReadModel);
    }

    @Override
    public List<EnrollmentSummaryReadModel> findByStudent(UUID studentId) {
        return enrollmentJpaRepository.findByStudentId(studentId).stream()
                .map(this::toSummaryReadModel).collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentSummaryReadModel> findByCourse(UUID courseId) {
        return enrollmentJpaRepository.findByCourseId(courseId).stream()
                .map(this::toSummaryReadModel).collect(Collectors.toList());
    }

    private EnrollmentReadModel toReadModel(EnrollmentJpaEntity e) {
        EnrollmentStatus status = e.getStatus() != null ? EnrollmentStatus.valueOf(e.getStatus()) : null;

        return new EnrollmentReadModel(e.getId(), e.getStudentId(), e.getCourseId(), status,
                e.getEnrolledAt(), e.getCompletedAt(), e.getCancelledAt());
    }

    private EnrollmentSummaryReadModel toSummaryReadModel(EnrollmentJpaEntity e) {
        EnrollmentStatus status = e.getStatus() != null ? EnrollmentStatus.valueOf(e.getStatus()) : null;

        return new EnrollmentSummaryReadModel(e.getId(), e.getStudentId(), e.getCourseId(), status, e.getEnrolledAt());
    }
}