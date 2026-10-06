package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdateTitleCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdateTitleUseCase {

    private final CourseRepositoryPort repository;

    public UpdateTitleUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdateTitleCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updateTitle(command.title());

        return repository.save(course);
    }
}