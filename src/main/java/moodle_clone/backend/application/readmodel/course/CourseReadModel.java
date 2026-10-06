package moodle_clone.backend.application.readmodel.course;

import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CourseReadModel(
    UUID id,
    String title,
    String description,
    String code,
    CourseStatus status,
    Set<UUID> instructors,
    int workload,
    int maxEnrollments,
    LocalDate startDate,
    LocalDate endDate,
    CourseCategory category
) { }