package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

import java.util.UUID;

public class PublishCourseUseCase {

    private final CourseRepositoryPort repository;

    public PublishCourseUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UUID courseId) {
        Course course = repository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(courseId));

        course.publish();

        return repository.save(course);
    }
}