package moodle_clone.backend.application.port.out.enrollment;

import moodle_clone.backend.domain.model.enrollment.Enrollment;

import java.util.Optional;
import java.util.UUID;

public interface EnrollmentRepositoryPort {

    Enrollment save(Enrollment enrollment);

    Optional<Enrollment> findById(UUID id);

    boolean existsActiveByStudentAndCourse(UUID studentId, UUID courseId);

    int countActiveByCourse(UUID courseId);
}