package moodle_clone.backend.adapter.in.web.controller;

import jakarta.validation.Valid;
import moodle_clone.backend.adapter.in.web.dto.course.*;
import moodle_clone.backend.application.port.in.course.*;
import moodle_clone.backend.application.readmodel.course.CourseSummaryReadModel;
import moodle_clone.backend.application.usecase.course.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final UpdateTitleUseCase updateTitleUseCase;
    private final UpdateDescriptionUseCase updateDescriptionUseCase;
    private final UpdateWorkloadUseCase updateWorkloadUseCase;
    private final UpdateMaxEnrollmentsUseCase updateMaxEnrollmentsUseCase;
    private final UpdatePeriodUseCase updatePeriodUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final AddInstructorUseCase addInstructorUseCase;
    private final RemoveInstructorUseCase removeInstructorUseCase;
    private final PublishCourseUseCase publishCourseUseCase;
    private final ArchiveCourseUseCase archiveCourseUseCase;
    private final FindCourseByIdUseCase findCourseByIdUseCase;
    private final ListCoursesUseCase listCoursesUseCase;

    public CourseController(CreateCourseUseCase createCourseUseCase,
                            UpdateTitleUseCase updateTitleUseCase,
                            UpdateDescriptionUseCase updateDescriptionUseCase,
                            UpdateWorkloadUseCase updateWorkloadUseCase,
                            UpdateMaxEnrollmentsUseCase updateMaxEnrollmentsUseCase,
                            UpdatePeriodUseCase updatePeriodUseCase,
                            UpdateCategoryUseCase updateCategoryUseCase,
                            AddInstructorUseCase addInstructorUseCase,
                            RemoveInstructorUseCase removeInstructorUseCase,
                            PublishCourseUseCase publishCourseUseCase,
                            ArchiveCourseUseCase archiveCourseUseCase,
                            FindCourseByIdUseCase findCourseByIdUseCase,
                            ListCoursesUseCase listCoursesUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.updateTitleUseCase = updateTitleUseCase;
        this.updateDescriptionUseCase = updateDescriptionUseCase;
        this.updateWorkloadUseCase = updateWorkloadUseCase;
        this.updateMaxEnrollmentsUseCase = updateMaxEnrollmentsUseCase;
        this.updatePeriodUseCase = updatePeriodUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.addInstructorUseCase = addInstructorUseCase;
        this.removeInstructorUseCase = removeInstructorUseCase;
        this.publishCourseUseCase = publishCourseUseCase;
        this.archiveCourseUseCase = archiveCourseUseCase;
        this.findCourseByIdUseCase = findCourseByIdUseCase;
        this.listCoursesUseCase = listCoursesUseCase;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CreateCourseRequest request) {
        var command = new CreateCourseCommand(request.title(), request.description(), request.code(),
                request.instructors(), request.workload(), request.maxEnrollments());
        var course = createCourseUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CourseResponse(
                course.getId(), course.getTitle(), course.getDescription(), course.getStatus(),
                course.getInstructors(), course.getWorkload(), course.getMaxEnrollments(),
                course.getStartDate(), course.getEndDate(), course.getCategory()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable UUID id) {
        var readModel = findCourseByIdUseCase.execute(id);
        return ResponseEntity.ok(CourseResponse.fromReadModel(readModel));
    }

    @GetMapping
    public ResponseEntity<List<CourseSummaryResponse>> list() {
        List<CourseSummaryReadModel> summaries = listCoursesUseCase.execute();
        return ResponseEntity.ok(summaries.stream().map(CourseSummaryResponse::fromReadModel).toList());
    }

    @PutMapping("/{id}/title")
    public ResponseEntity<Void> updateTitle(@PathVariable UUID id, @Valid @RequestBody UpdateTitleRequest request) {
        updateTitleUseCase.execute(new UpdateTitleCommand(id, request.title()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Void> updateDescription(@PathVariable UUID id, @RequestBody UpdateDescriptionRequest request) {
        updateDescriptionUseCase.execute(new UpdateDescriptionCommand(id, request.description()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/workload")
    public ResponseEntity<Void> updateWorkload(@PathVariable UUID id, @Valid @RequestBody UpdateWorkloadRequest request) {
        updateWorkloadUseCase.execute(new UpdateWorkloadCommand(id, request.workload()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/max-enrollments")
    public ResponseEntity<Void> updateMaxEnrollments(@PathVariable UUID id, @Valid @RequestBody UpdateMaxEnrollmentsRequest request) {
        updateMaxEnrollmentsUseCase.execute(new UpdateMaxEnrollmentsCommand(id, request.maxEnrollments()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/period")
    public ResponseEntity<Void> updatePeriod(@PathVariable UUID id, @RequestBody UpdatePeriodRequest request) {
        updatePeriodUseCase.execute(new UpdatePeriodCommand(id, request.startDate(), request.endDate()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Void> updateCategory(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryRequest request) {
        updateCategoryUseCase.execute(new UpdateCategoryCommand(id, request.category()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/instructors")
    public ResponseEntity<Void> addInstructor(@PathVariable UUID id, @Valid @RequestBody InstructorRequest request) {
        addInstructorUseCase.execute(new InstructorCommand(id, request.instructorId()));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/instructors/{instructorId}")
    public ResponseEntity<Void> removeInstructor(@PathVariable UUID id, @PathVariable UUID instructorId) {
        removeInstructorUseCase.execute(new InstructorCommand(id, instructorId));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<Void> publish(@PathVariable UUID id) {
        publishCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<Void> archive(@PathVariable UUID id) {
        archiveCourseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}