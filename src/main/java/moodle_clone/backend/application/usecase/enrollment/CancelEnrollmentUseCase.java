package moodle_clone.backend.application.usecase.enrollment;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.enrollment.EnrollmentIdCommand;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentRepositoryPort;
import moodle_clone.backend.domain.model.enrollment.Enrollment;

public class CancelEnrollmentUseCase {

    private final EnrollmentRepositoryPort repository;

    public CancelEnrollmentUseCase(EnrollmentRepositoryPort repository) {
        this.repository = repository;
    }

    public Enrollment execute(EnrollmentIdCommand command) {
        Enrollment enrollment = repository.findById(command.enrollmentId())
                .orElseThrow(() -> new NotFoundException(command.enrollmentId()));

        enrollment.cancel();

        return repository.save(enrollment);
    }
}