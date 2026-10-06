package moodle_clone.backend.application.usecase.enrollment;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentQueryPort;
import moodle_clone.backend.application.readmodel.enrollment.EnrollmentReadModel;

import java.util.UUID;

public class FindEnrollmentByIdUseCase {

    private final EnrollmentQueryPort queryPort;

    public FindEnrollmentByIdUseCase(EnrollmentQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public EnrollmentReadModel execute(UUID enrollmentId) {
        return queryPort.findReadModelById(enrollmentId)
                .orElseThrow(() -> new NotFoundException(enrollmentId));
    }
}