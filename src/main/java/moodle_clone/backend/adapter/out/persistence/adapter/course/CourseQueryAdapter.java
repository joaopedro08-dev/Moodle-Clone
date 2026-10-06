package moodle_clone.backend.adapter.out.persistence.adapter.course;

import moodle_clone.backend.adapter.out.persistence.entity.course.CourseJpaEntity;
import moodle_clone.backend.adapter.out.persistence.repository.course.CourseJpaRepository;
import moodle_clone.backend.adapter.out.persistence.repository.enrollment.EnrollmentJpaRepository;
import moodle_clone.backend.application.port.out.course.CourseQueryPort;
import moodle_clone.backend.application.readmodel.course.CourseReadModel;
import moodle_clone.backend.application.readmodel.course.CourseSummaryReadModel;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CourseQueryAdapter implements CourseQueryPort {

    private final CourseJpaRepository jpaRepository;
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public CourseQueryAdapter(CourseJpaRepository jpaRepository,
                              EnrollmentJpaRepository enrollmentJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @Override
    public Optional<CourseReadModel> findReadModelById(UUID id) {
        return jpaRepository.findById(id).map(this::toReadModel);
    }

    @Override
    public List<CourseSummaryReadModel> findAllSummaries() {
        return jpaRepository.findAll().stream()
                .map(this::toSummaryReadModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseSummaryReadModel> findByInstructor(UUID instructorId) {
        return jpaRepository.findByInstructorsContaining(instructorId).stream()
                .map(this::toSummaryReadModel)
                .collect(Collectors.toList());
    }

    private CourseReadModel toReadModel(CourseJpaEntity e) {
        CourseStatus status = e.getStatus() != null ? CourseStatus.valueOf(e.getStatus()) : null;
        CourseCategory category = e.getCategory() != null ? CourseCategory.valueOf(e.getCategory()) : null;

        return new CourseReadModel(
                e.getId(), e.getTitle(), e.getDescription(), e.getCode(), status, e.getInstructors(),
                e.getWorkload(), e.getMaxEnrollments(), e.getStartDate(), e.getEndDate(), category
        );
    }

    private CourseSummaryReadModel toSummaryReadModel(CourseJpaEntity e) {
        CourseStatus status = e.getStatus() != null ? CourseStatus.valueOf(e.getStatus()) : null;
        CourseCategory category = e.getCategory() != null ? CourseCategory.valueOf(e.getCategory()) : null;
        int enrolledCount = enrollmentJpaRepository.countActiveByCourse(e.getId());

        return new CourseSummaryReadModel(
                e.getId(), e.getTitle(), e.getCode(), status, category,
                e.getWorkload(), enrolledCount, e.getMaxEnrollments()
        );
    }
}