package moodle_clone.backend.adapter.out.persistence.adapter.enrollment;

import moodle_clone.backend.adapter.out.persistence.entity.enrollment.EnrollmentJpaEntity;
import moodle_clone.backend.adapter.out.persistence.mapper.enrollment.EnrollmentMapper;
import moodle_clone.backend.adapter.out.persistence.repository.enrollment.EnrollmentJpaRepository;
import moodle_clone.backend.application.port.out.enrollment.EnrollmentRepositoryPort;
import moodle_clone.backend.domain.model.enrollment.Enrollment;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EnrollmentRepositoryAdapter implements EnrollmentRepositoryPort {

    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public EnrollmentRepositoryAdapter(EnrollmentJpaRepository enrollmentJpaRepository) {
        this.enrollmentJpaRepository = enrollmentJpaRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentJpaEntity entity = EnrollmentMapper.toJpaEntity(enrollment);
        EnrollmentJpaEntity saved = enrollmentJpaRepository.save(entity);
        return EnrollmentMapper.toDomain(saved);
    }

    @Override
    public Optional<Enrollment> findById(UUID id) {
        return enrollmentJpaRepository.findById(id).map(EnrollmentMapper::toDomain);
    }

    @Override
    public boolean existsActiveByStudentAndCourse(UUID studentId, UUID courseId) {
        return enrollmentJpaRepository.existsActiveByStudentAndCourse(studentId, courseId);
    }

    @Override
    public int countActiveByCourse(UUID courseId) {
        return enrollmentJpaRepository.countActiveByCourse(courseId);
    }
}