package moodle_clone.backend.adapter.in.web.dto.course;

import moodle_clone.backend.application.readmodel.course.CourseReadModel;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CourseResponse(
        UUID id,
        String title,
        String description,
        CourseStatus status,
        Set<UUID> instructors,
        int workload,
        int maxEnrollments,
        LocalDate startDate,
        LocalDate endDate,
        CourseCategory category
) {
    public static CourseResponse fromReadModel(CourseReadModel rm) {
        return new CourseResponse(
                rm.id(), rm.title(), rm.description(), rm.status(), rm.instructors(),
                rm.workload(), rm.maxEnrollments(), rm.startDate(), rm.endDate(), rm.category()
        );
    }
}