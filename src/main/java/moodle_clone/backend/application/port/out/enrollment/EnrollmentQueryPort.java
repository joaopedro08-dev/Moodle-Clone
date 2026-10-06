package moodle_clone.backend.application.port.out.enrollment;

import moodle_clone.backend.application.readmodel.enrollment.EnrollmentReadModel;
import moodle_clone.backend.application.readmodel.enrollment.EnrollmentSummaryReadModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrollmentQueryPort {

    Optional<EnrollmentReadModel> findReadModelById(UUID id);

    List<EnrollmentSummaryReadModel> findByStudent(UUID studentId);

    List<EnrollmentSummaryReadModel> findByCourse(UUID courseId);
}