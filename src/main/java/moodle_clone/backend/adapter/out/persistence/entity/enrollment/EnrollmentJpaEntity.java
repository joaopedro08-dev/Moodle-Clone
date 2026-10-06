package moodle_clone.backend.adapter.out.persistence.entity.enrollment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import moodle_clone.backend.adapter.out.persistence.entity.base.BaseJpaEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentJpaEntity extends BaseJpaEntity {

    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "enrolled_at", nullable = false)
    private LocalDate enrolledAt;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @Column(name = "cancelled_at")
    private LocalDate cancelledAt;
}