package moodle_clone.backend.adapter.out.persistence.repository.course;

import moodle_clone.backend.adapter.out.persistence.entity.course.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, UUID> {

    boolean existsByCode(String code);

    @Query("SELECT c FROM CourseJpaEntity c JOIN c.instructors i WHERE i = :instructorId")
    List<CourseJpaEntity> findByInstructorsContaining(@Param("instructorId") UUID instructorId);
}