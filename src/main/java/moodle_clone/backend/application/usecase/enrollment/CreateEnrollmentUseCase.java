package moodle_clone.backend.application.usecase.enrollment;

import moodle_clone.backend.application.exception.ConflictException;
import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.enrollment.CreateEnrollmentCommand;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentRepositoryPort;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;
import moodle_clone.backend.domain.model.enrollment.Enrollment;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;
import moodle_clone.backend.domain.model.enums.user.Roles;
import moodle_clone.backend.domain.model.user.User;

import java.util.UUID;

public class CreateEnrollmentUseCase {

    private final EnrollmentRepositoryPort enrollmentRepository;
    private final CourseRepositoryPort courseRepository;
    private final UserRepositoryPort userRepository;

    public CreateEnrollmentUseCase(EnrollmentRepositoryPort enrollmentRepository,
                                   CourseRepositoryPort courseRepository,
                                   UserRepositoryPort userRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Enrollment execute(CreateEnrollmentCommand command) {
        User student = userRepository.findById(command.studentId())
                .orElseThrow(() -> new NotFoundException(command.studentId()));

        if (!student.hasRole(Roles.STUDENT)) {
            throw new IllegalStateException("Usuário não possui o papel de aluno");
        }

        Course course = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new NotFoundException(command.courseId()));

        if (course.getStatus() != CourseStatus.PUBLISHED) {
            throw new IllegalStateException("Curso não está publicado para matrículas");
        }

        if (enrollmentRepository.existsActiveByStudentAndCourse(command.studentId(), command.courseId())) {
            throw new ConflictException("Aluno já possui matrícula ativa neste curso");
        }

        int activeCount = enrollmentRepository.countActiveByCourse(command.courseId());
        if (activeCount >= course.getMaxEnrollments()) {
            throw new IllegalStateException("Curso atingiu o limite máximo de vagas");
        }

        Enrollment enrollment = new Enrollment(UUID.randomUUID(), command.studentId(), command.courseId());

        return enrollmentRepository.save(enrollment);
    }
}