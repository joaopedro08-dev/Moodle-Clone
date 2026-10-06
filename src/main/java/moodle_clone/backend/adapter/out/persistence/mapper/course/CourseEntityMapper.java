package moodle_clone.backend.adapter.out.persistence.mapper.course;

import moodle_clone.backend.adapter.out.persistence.entity.course.CourseJpaEntity;
import moodle_clone.backend.domain.model.course.Course;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

public class CourseEntityMapper {

    public static CourseJpaEntity toJpaEntity(Course course) {
        CourseJpaEntity entity = new CourseJpaEntity();
        entity.setId(course.getId());
        entity.setCreatedAt(course.getCreatedAt());
        entity.setUpdatedAt(course.getUpdatedAt());
        entity.setTitle(course.getTitle());
        entity.setDescription(course.getDescription());
        entity.setCode(course.getCode());
        entity.setStatus(course.getStatus() != null ? course.getStatus().name() : null);
        entity.setInstructors(course.getInstructors());
        entity.setWorkload(course.getWorkload());
        entity.setMaxEnrollments(course.getMaxEnrollments());
        entity.setStartDate(course.getStartDate());
        entity.setEndDate(course.getEndDate());
        entity.setCategory(course.getCategory() != null ? course.getCategory().name() : null);

        return entity;
    }

    public static Course toDomain(CourseJpaEntity entity) {
        CourseStatus status = entity.getStatus() != null ?
                CourseStatus.valueOf(entity.getStatus()) : null;

        CourseCategory category = entity.getCategory() != null ?
                CourseCategory.valueOf(entity.getCategory()) : null;

        return Course.reconstruct(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(),
                entity.getTitle(), entity.getDescription(), entity.getCode(), status,
                new java.util.HashSet<>(entity.getInstructors()), entity.getWorkload(), entity.getMaxEnrollments(),
                entity.getStartDate(), entity.getEndDate(), category);
    }
}