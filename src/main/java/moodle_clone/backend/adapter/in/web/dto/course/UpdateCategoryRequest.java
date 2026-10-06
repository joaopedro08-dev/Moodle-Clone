package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.NotNull;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;

public record UpdateCategoryRequest(
        @NotNull(message = "A categoria é obrigatória") CourseCategory category
) {}