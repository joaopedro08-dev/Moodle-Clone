package moodle_clone.backend.application.readmodel.course;

import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

import java.util.UUID;

public record CourseSummaryReadModel(
        UUID id,
        String title,
        String code,
        CourseStatus status,
        CourseCategory category,
        int workload,
        int enrolledCount,
        int maxEnrollments
) {}