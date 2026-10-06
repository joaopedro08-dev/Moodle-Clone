package moodle_clone.backend.adapter.out.persistence.entity.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import moodle_clone.backend.adapter.out.persistence.entity.base.BaseJpaEntity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseJpaEntity extends BaseJpaEntity {

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "code", length = 30, unique = true, nullable = false)
    private String code;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_course_instructors", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "instructor_id", nullable = false)
    private Set<UUID> instructors;

    @Column(name = "workload", nullable = false)
    private int workload;

    @Column(name = "max_enrollments", nullable = false)
    private int maxEnrollments;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "category", length = 30)
    private String category;
}