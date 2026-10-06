package moodle_clone.backend.adapter.in.web.dto.course;

import moodle_clone.backend.application.readmodel.course.CourseSummaryReadModel;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

import java.util.UUID;

public record CourseSummaryResponse(
        UUID id,
        String title,
        String code,
        CourseStatus status,
        CourseCategory category,
        int workload,
        int enrolledCount,
        int maxEnrollments
) {
    public static CourseSummaryResponse fromReadModel(CourseSummaryReadModel rm) {
        return new CourseSummaryResponse(
                rm.id(), rm.title(), rm.code(), rm.status(), rm.category(),
                rm.workload(), rm.enrolledCount(), rm.maxEnrollments()
        );
    }
}