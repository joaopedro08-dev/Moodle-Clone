package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdateMaxEnrollmentsCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdateMaxEnrollmentsUseCase {

    private final CourseRepositoryPort repository;

    public UpdateMaxEnrollmentsUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdateMaxEnrollmentsCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updateMaxEnrollments(command.maxEnrollments());

        return repository.save(course);
    }
}