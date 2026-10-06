package moodle_clone.backend.config;

import moodle_clone.backend.application.port.out.course.CourseQueryPort;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentQueryPort;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentRepositoryPort;
import moodle_clone.backend.application.port.out.user.UserQueryPort;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.application.usecase.course.*;
import moodle_clone.backend.application.usecase.enrollment.*;
import moodle_clone.backend.application.usecase.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ActivateUserUseCase activateUserUseCase(UserRepositoryPort repository) {
        return new ActivateUserUseCase(repository);
    }

    @Bean
    public AddRoleToUserUseCase addRoleToUserUseCase(UserRepositoryPort repository) {
        return new AddRoleToUserUseCase(repository);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort repository) {
        return new CreateUserUseCase(repository);
    }

    @Bean
    public DeactivateUserUseCase deactivateUserUseCase(UserRepositoryPort repository) {
        return new DeactivateUserUseCase(repository);
    }

    @Bean
    public FindUserByIdUseCase findUserByIdUseCase(UserQueryPort queryPort) {
        return new FindUserByIdUseCase(queryPort);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserQueryPort queryPort) {
        return new ListUsersUseCase(queryPort);
    }

    @Bean
    public RemoveRoleFromUserUseCase removeRoleFromUserUseCase(UserRepositoryPort repository) {
        return new RemoveRoleFromUserUseCase(repository);
    }

    @Bean
    public UpdateUserNameUseCase updateUserNameUseCase(UserRepositoryPort repository) {
        return new UpdateUserNameUseCase(repository);
    }

    @Bean
    public UpdateUserEmailUseCase updateUserEmailUseCase(UserRepositoryPort repository) {
        return new UpdateUserEmailUseCase(repository);
    }

    @Bean
    public UpdateUserAddressUseCase updateUserAddressUseCase(UserRepositoryPort repository) {
        return new UpdateUserAddressUseCase(repository);
    }

    @Bean
    public UpdateUserDateBirthUseCase updateUserDateBirthUseCase(UserRepositoryPort repository) {
        return new UpdateUserDateBirthUseCase(repository);
    }

    @Bean
    public UpdateUserNationalityUseCase updateUserNationalityUseCase(UserRepositoryPort repository) {
        return new UpdateUserNationalityUseCase(repository);
    }

    @Bean
    public UpdateUserPhoneUseCase updateUserPhoneUseCase(UserRepositoryPort repository) {
        return new UpdateUserPhoneUseCase(repository);
    }

    @Bean
    public AddInstructorUseCase addInstructorUseCase(CourseRepositoryPort courseRepositoryPort, UserRepositoryPort userRepositoryPort) {
        return new AddInstructorUseCase(courseRepositoryPort, userRepositoryPort);
    }

    @Bean
    public ArchiveCourseUseCase archiveCourseUseCase(CourseRepositoryPort repository) {
        return new ArchiveCourseUseCase(repository);
    }

    @Bean
    public CreateCourseUseCase createCourseUseCase(CourseRepositoryPort repository) {
        return new CreateCourseUseCase(repository);
    }

    @Bean
    public FindCourseByIdUseCase findCourseByIdUseCase(CourseQueryPort query) {
        return new FindCourseByIdUseCase(query);
    }

    @Bean
    public ListCoursesUseCase listCoursesUseCase(CourseQueryPort query) {
        return new ListCoursesUseCase(query);
    }

    @Bean
    public PublishCourseUseCase publishCourseUseCase(CourseRepositoryPort repository) {
        return new PublishCourseUseCase(repository);
    }

    @Bean
    public RemoveInstructorUseCase removeInstructorUseCase(CourseRepositoryPort repository) {
        return new RemoveInstructorUseCase(repository);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CourseRepositoryPort repository) {
        return new UpdateCategoryUseCase(repository);
    }

    @Bean
    public UpdateDescriptionUseCase updateDescriptionUseCase(CourseRepositoryPort repository) {
        return new UpdateDescriptionUseCase(repository);
    }

    @Bean
    public UpdateMaxEnrollmentsUseCase updateMaxEnrollmentsUseCase(CourseRepositoryPort repository) {
        return new UpdateMaxEnrollmentsUseCase(repository);
    }

    @Bean
    public UpdatePeriodUseCase updatePeriodUseCase(CourseRepositoryPort repository) {
        return new UpdatePeriodUseCase(repository);
    }

    @Bean
    public UpdateTitleUseCase updateTitleUseCase(CourseRepositoryPort repository) {
        return new UpdateTitleUseCase(repository);
    }

    @Bean
    public UpdateWorkloadUseCase updateWorkloadUseCase(CourseRepositoryPort repository) {
        return new UpdateWorkloadUseCase(repository);
    }

    @Bean
    public CancelEnrollmentUseCase cancelEnrollmentUseCase(EnrollmentRepositoryPort repository) {
        return new CancelEnrollmentUseCase(repository);
    }

    @Bean
    public CompleteEnrollmentUseCase completeEnrollmentUseCase(EnrollmentRepositoryPort repository) {
        return new CompleteEnrollmentUseCase(repository);
    }

    @Bean
    public CreateEnrollmentUseCase createEnrollmentUseCase(EnrollmentRepositoryPort enrollmentRepositoryPort, CourseRepositoryPort courseRepositoryPort, UserRepositoryPort userRepositoryPort) {
        return new CreateEnrollmentUseCase(enrollmentRepositoryPort, courseRepositoryPort, userRepositoryPort);
    }

    @Bean
    public FindEnrollmentByIdUseCase findEnrollmentByIdUseCase(EnrollmentQueryPort query) {
        return new FindEnrollmentByIdUseCase(query);
    }

    @Bean
    public ListEnrollmentsByCourseUseCase listEnrollmentsByCourseUseCase(EnrollmentQueryPort query) {
        return new ListEnrollmentsByCourseUseCase(query);
    }

    @Bean
    public ListEnrollmentsByStudentUseCase listEnrollmentsByStudentUseCase(EnrollmentQueryPort query) {
        return new ListEnrollmentsByStudentUseCase(query);
    }
}