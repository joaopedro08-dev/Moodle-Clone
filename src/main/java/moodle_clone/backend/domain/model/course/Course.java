package moodle_clone.backend.domain.model.course;

import moodle_clone.backend.domain.base.BaseEntity;
import moodle_clone.backend.domain.exception.course.InvalidCourseException;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Course extends BaseEntity {

    private String title;
    private String description;
    private final String code;
    private CourseStatus status;
    private final Set<UUID> instructors;
    private int workload;
    private int maxEnrollments;
    private LocalDate startDate;
    private LocalDate endDate;
    private CourseCategory category;

    public Course(UUID id, String title, String description, String code,
                  Set<UUID> instructors, int workload, int maxEnrollments) {
        super(id);

        if (title == null || title.isBlank()) {
            throw new InvalidCourseException("Título do curso não pode ser vazio");
        }
        if (code == null || code.isBlank()) {
            throw new InvalidCourseException("Código do curso não pode ser vazio");
        }
        if (instructors == null || instructors.isEmpty()) {
            throw new InvalidCourseException("Curso precisa de ao menos um instrutor");
        }
        if (workload <= 0) {
            throw new InvalidCourseException("Carga horária deve ser maior que zero");
        }
        if (maxEnrollments <= 0) {
            throw new InvalidCourseException("Limite de vagas deve ser maior que zero");
        }

        this.title = title;
        this.description = description;
        this.code = code;
        this.instructors = new HashSet<>(instructors);
        this.workload = workload;
        this.maxEnrollments = maxEnrollments;
        this.status = CourseStatus.DRAFT;
    }

    private Course(UUID id, Instant createdAt, Instant updatedAt, String title, String description,
                   String code, CourseStatus status, Set<UUID> instructors, int workload,
                   int maxEnrollments, LocalDate startDate, LocalDate endDate, CourseCategory category) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.code = code;
        this.status = status;
        this.instructors = instructors;
        this.workload = workload;
        this.maxEnrollments = maxEnrollments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
    }

    public static Course reconstruct(UUID id, Instant createdAt, Instant updatedAt, String title,
                                     String description, String code, CourseStatus status,
                                     Set<UUID> instructors, int workload, int maxEnrollments,
                                     LocalDate startDate, LocalDate endDate, CourseCategory category) {
        return new Course(id, createdAt, updatedAt, title, description, code, status,
                instructors, workload, maxEnrollments, startDate, endDate, category);
    }

    public void publish() {
        if (this.status != CourseStatus.DRAFT) {
            throw new IllegalStateException("Curso não está disponível para publicação");
        }
        if (this.instructors.isEmpty()) {
            throw new IllegalStateException("Curso não pode ser publicado sem instrutores");
        }
        this.status = CourseStatus.PUBLISHED;
        markUpdated();
    }

    public void archive() {
        if (this.status == CourseStatus.ARCHIVED) {
            throw new IllegalStateException("Curso já está arquivado");
        }
        this.status = CourseStatus.ARCHIVED;
        markUpdated();
    }

    public void addInstructor(UUID instructorId) {
        ensureNotArchived();
        this.instructors.add(instructorId);
        markUpdated();
    }

    public void removeInstructor(UUID instructorId) {
        ensureNotArchived();
        if (this.instructors.size() == 1 && this.instructors.contains(instructorId)) {
            throw new IllegalStateException("Curso precisa manter ao menos um instrutor");
        }
        this.instructors.remove(instructorId);
        markUpdated();
    }

    public void updateTitle(String newTitle) {
        ensureNotArchived();
        if (newTitle == null || newTitle.isBlank()) {
            throw new InvalidCourseException("Título do curso não pode ser vazio");
        }
        this.title = newTitle;
        markUpdated();
    }

    public void updateDescription(String newDescription) {
        ensureNotArchived();
        this.description = newDescription;
        markUpdated();
    }

    public void updateWorkload(int newWorkload) {
        ensureNotArchived();
        if (newWorkload <= 0) {
            throw new InvalidCourseException("Carga horária deve ser maior que zero");
        }
        this.workload = newWorkload;
        markUpdated();
    }

    public void updateMaxEnrollments(int newMaxEnrollments) {
        ensureNotArchived();
        if (newMaxEnrollments <= 0) {
            throw new InvalidCourseException("Limite de vagas deve ser maior que zero");
        }
        this.maxEnrollments = newMaxEnrollments;
        markUpdated();
    }

    public void updatePeriod(LocalDate newStartDate, LocalDate newEndDate) {
        ensureNotArchived();
        if (newStartDate != null && newEndDate != null && newStartDate.isAfter(newEndDate)) {
            throw new InvalidCourseException("Data de início não pode ser depois da data de término");
        }
        this.startDate = newStartDate;
        this.endDate = newEndDate;
        markUpdated();
    }

    public void updateCategory(CourseCategory newCategory) {
        ensureNotArchived();
        this.category = newCategory;
        markUpdated();
    }

    private void ensureNotArchived() {
        if (this.status == CourseStatus.ARCHIVED) {
            throw new IllegalStateException("Curso arquivado não pode ser modificado");
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCode() { return code; }
    public CourseStatus getStatus() { return status; }
    public Set<UUID> getInstructors() { return Set.copyOf(instructors); }
    public int getWorkload() { return workload; }
    public int getMaxEnrollments() { return maxEnrollments; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public CourseCategory getCategory() { return category; }
}