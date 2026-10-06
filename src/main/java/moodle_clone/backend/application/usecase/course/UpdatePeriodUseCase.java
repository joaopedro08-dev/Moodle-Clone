package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdatePeriodCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdatePeriodUseCase {

    private final CourseRepositoryPort repository;

    public UpdatePeriodUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdatePeriodCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updatePeriod(command.startDate(), command.endDate());

        return repository.save(course);
    }
}