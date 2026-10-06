package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.UpdateWorkloadCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class UpdateWorkloadUseCase {

    private final CourseRepositoryPort repository;

    public UpdateWorkloadUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(UpdateWorkloadCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.updateWorkload(command.workload());

        return repository.save(course);
    }
}