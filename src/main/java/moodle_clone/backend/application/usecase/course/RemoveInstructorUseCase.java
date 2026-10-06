package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.InstructorCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class RemoveInstructorUseCase {

    private final CourseRepositoryPort repository;

    public RemoveInstructorUseCase(CourseRepositoryPort repository) {
        this.repository = repository;
    }

    public Course execute(InstructorCommand command) {
        Course course = repository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        course.removeInstructor(command.instructorId());

        return repository.save(course);
    }
}