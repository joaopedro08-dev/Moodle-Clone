package moodle_clone.backend.application.port.in.course;

import moodle_clone.backend.domain.model.enums.course.CourseCategory;

import java.util.UUID;

public record UpdateCategoryCommand(UUID courseId, CourseCategory category) {}