package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdateDescriptionCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdateDescriptionUseCase {

    private final CourseRepositoryPort repository;

    public UpdateDescriptionUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdateDescriptionCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updateDescription(command.description());

        return repository.save(course);
    }
}