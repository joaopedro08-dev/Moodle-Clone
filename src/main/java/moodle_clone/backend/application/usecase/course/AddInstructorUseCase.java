package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.course.InstructorCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;

public class AddInstructorUseCase {

    private final CourseRepositoryPort courseRepository;
    private final UserRepositoryPort userRepository;

    public AddInstructorUseCase(CourseRepositoryPort courseRepository, UserRepositoryPort userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course execute(InstructorCommand command) {
        Course course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        if (userRepository.findById(command.instructorId()).isEmpty()) {
            throw new NotFoundException(command.instructorId());
        }

        course.addInstructor(command.instructorId());

        return courseRepository.save(course);
    }
}