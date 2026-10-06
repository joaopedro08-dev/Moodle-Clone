package moodle_clone.backend.adapter.out.persistence.repository.enrollment;

import moodle_clone.backend.adapter.out.persistence.entity.enrollment.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentJpaEntity, UUID> {

    @Query("""
        SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
        FROM EnrollmentJpaEntity e
        WHERE e.studentId = :studentId
          AND e.courseId = :courseId
          AND e.status = 'ACTIVE'
        """)
    boolean existsActiveByStudentAndCourse(@Param("studentId") UUID studentId, @Param("courseId") UUID courseId);

    @Query("""
        SELECT COUNT(e)
        FROM EnrollmentJpaEntity e
        WHERE e.courseId = :courseId
          AND e.status = 'ACTIVE'
        """)
    int countActiveByCourse(@Param("courseId") UUID courseId);

    List<EnrollmentJpaEntity> findByStudentId(UUID studentId);

    List<EnrollmentJpaEntity> findByCourseId(UUID courseId);
}