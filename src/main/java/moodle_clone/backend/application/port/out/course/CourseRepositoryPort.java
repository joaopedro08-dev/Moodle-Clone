package moodle_clone.backend.application.port.out.course;

import moodle_clone.backend.domain.model.course.Course;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepositoryPort {

    Course save(Course course);

    Optional<Course> findById(UUID id);

    boolean existsByCode(String code);
}