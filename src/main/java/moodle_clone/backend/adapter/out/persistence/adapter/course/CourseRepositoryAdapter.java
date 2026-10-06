package moodle_clone.backend.adapter.out.persistence.adapter.course;

import moodle_clone.backend.adapter.out.persistence.entity.course.CourseJpaEntity;
import moodle_clone.backend.adapter.out.persistence.mapper.course.CourseEntityMapper;
import moodle_clone.backend.adapter.out.persistence.repository.course.CourseJpaRepository;
import moodle_clone.backend.application.port.out.course.CourseRepositoryPort;
import moodle_clone.backend.domain.model.course.Course;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CourseRepositoryAdapter implements CourseRepositoryPort {

    private final CourseJpaRepository jpaRepository;

    public CourseRepositoryAdapter(CourseJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Course save(Course course) {
        CourseJpaEntity entity = CourseEntityMapper.toJpaEntity(course);
        CourseJpaEntity saved = jpaRepository.save(entity);
        return CourseEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return jpaRepository.findById(id).map(CourseEntityMapper::toDomain);
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }
}