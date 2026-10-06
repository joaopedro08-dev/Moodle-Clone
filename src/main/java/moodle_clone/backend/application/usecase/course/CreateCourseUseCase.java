package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.ConflictException;
import moodle_clone.backend.application.port.in.course.CreateCourseCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

import java.util.UUID;

public class CreateCourseUseCase {

    private final CourseRepositoryPort repository;

    public CreateCourseUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(CreateCourseCommand command) {
        if (repository.existsByCode(command.code())) {
            throw new ConflictException(command.code());
        }

        Course course = new Course(
                UUID.randomUUID(),
                command.title(),
                command.description(),
                command.code(),
                command.instructors(),
                command.workload(),
                command.maxEnrollments()
        );

        return repository.save(course);
    }
}