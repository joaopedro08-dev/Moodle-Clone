package moodle_clone.backend.domain.model.enrollment;

import moodle_clone.backend.domain.base.BaseEntity;
import moodle_clone.backend.domain.exception.enrollment.InvalidEnrollmentException;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class Enrollment extends BaseEntity {

    private final UUID studentId;
    private final UUID courseId;
    private EnrollmentStatus status;
    private final LocalDate enrolledAt;
    private LocalDate completedAt;
    private LocalDate cancelledAt;

    public Enrollment(UUID id, UUID studentId, UUID courseId) {
        super(id);

        if (studentId == null) {
            throw new InvalidEnrollmentException("Id do aluno é obrigatório");
        }
        if (courseId == null) {
            throw new InvalidEnrollmentException("Id do curso é obrigatório");
        }

        this.studentId = studentId;
        this.courseId = courseId;
        this.status = EnrollmentStatus.ACTIVE;
        this.enrolledAt = LocalDate.now();
    }

    private Enrollment(UUID id, Instant createdAt, Instant updatedAt, UUID studentId, UUID courseId,
                       EnrollmentStatus status, LocalDate enrolledAt, LocalDate completedAt, LocalDate cancelledAt) {
        super(id, createdAt, updatedAt);
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
        this.enrolledAt = enrolledAt;
        this.completedAt = completedAt;
        this.cancelledAt = cancelledAt;
    }

    public static Enrollment reconstruct(UUID id, Instant createdAt, Instant updatedAt, UUID studentId,
                                         UUID courseId, EnrollmentStatus status, LocalDate enrolledAt,
                                         LocalDate completedAt, LocalDate cancelledAt) {
        return new Enrollment(id, createdAt, updatedAt, studentId, courseId, status,
                enrolledAt, completedAt, cancelledAt);
    }

    public void complete() {
        if (this.status != EnrollmentStatus.ACTIVE) {
            throw new IllegalStateException("Só é possível concluir uma matrícula ativa");
        }
        this.status = EnrollmentStatus.COMPLETED;
        this.completedAt = LocalDate.now();
        markUpdated();
    }

    public void cancel() {
        if (this.status != EnrollmentStatus.ACTIVE) {
            throw new IllegalStateException("Só é possível cancelar uma matrícula ativa");
        }
        this.status = EnrollmentStatus.CANCELLED;
        this.cancelledAt = LocalDate.now();
        markUpdated();
    }

    public boolean isActive() {
        return this.status == EnrollmentStatus.ACTIVE;
    }

    public UUID getStudentId() { return studentId; }
    public UUID getCourseId() { return courseId; }
    public EnrollmentStatus getStatus() { return status; }
    public LocalDate getEnrolledAt() { return enrolledAt; }
    public LocalDate getCompletedAt() { return completedAt; }
    public LocalDate getCancelledAt() { return cancelledAt; }
}