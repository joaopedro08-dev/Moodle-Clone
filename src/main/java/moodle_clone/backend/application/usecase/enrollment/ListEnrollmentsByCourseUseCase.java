package moodle_clone.backend.application.usecase.enrollment;

import moodle_clone.backend.application.port.out.enrollment.EnrollmentQueryPort;
import moodle_clone.backend.application.readmodel.enrollment.EnrollmentSummaryReadModel;

import java.util.List;
import java.util.UUID;

public class ListEnrollmentsByCourseUseCase {

    private final EnrollmentQueryPort queryPort;

    public ListEnrollmentsByCourseUseCase(EnrollmentQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public List<EnrollmentSummaryReadModel> execute(UUID courseId) {
        return queryPort.findByCourse(courseId);
    }
}