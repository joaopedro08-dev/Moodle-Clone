package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdateCategoryCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdateCategoryUseCase {

    private final CourseRepositoryPort repository;

    public UpdateCategoryUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdateCategoryCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updateCategory(command.category());

        return repository.save(course);
    }
}
