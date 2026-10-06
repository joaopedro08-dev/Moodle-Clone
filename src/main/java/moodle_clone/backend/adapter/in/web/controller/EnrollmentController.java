package moodle_clone.backend.adapter.in.web.controller;

import jakarta.validation.Valid;
import moodle_clone.backend.adapter.in.web.dto.enrollment.CreateEnrollmentRequest;
import moodle_clone.backend.adapter.in.web.dto.enrollment.EnrollmentResponse;
import moodle_clone.backend.adapter.in.web.dto.enrollment.EnrollmentSummaryResponse;
import moodle_clone.backend.application.port.in.enrollment.CreateEnrollmentCommand;
import moodle_clone.backend.application.port.in.enrollment.EnrollmentIdCommand;
import moodle_clone.backend.application.usecase.enrollment.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final CancelEnrollmentUseCase cancelEnrollmentUseCase;
    private final CompleteEnrollmentUseCase completeEnrollmentUseCase;
    private final CreateEnrollmentUseCase createEnrollmentUseCase;
    private final FindEnrollmentByIdUseCase findEnrollmentByIdUseCase;
    private final ListEnrollmentsByCourseUseCase listEnrollmentsByCourseUseCase;
    private final ListEnrollmentsByStudentUseCase listEnrollmentsByStudentUseCase;

    public EnrollmentController(CancelEnrollmentUseCase cancelEnrollmentUseCase,
                                CompleteEnrollmentUseCase completeEnrollmentUseCase,
                                CreateEnrollmentUseCase createEnrollmentUseCase,
                                FindEnrollmentByIdUseCase findEnrollmentByIdUseCase,
                                ListEnrollmentsByCourseUseCase listEnrollmentsByCourseUseCase,
                                ListEnrollmentsByStudentUseCase listEnrollmentsByStudentUseCase) {
        this.cancelEnrollmentUseCase = cancelEnrollmentUseCase;
        this.completeEnrollmentUseCase = completeEnrollmentUseCase;
        this.createEnrollmentUseCase = createEnrollmentUseCase;
        this.findEnrollmentByIdUseCase = findEnrollmentByIdUseCase;
        this.listEnrollmentsByCourseUseCase = listEnrollmentsByCourseUseCase;
        this.listEnrollmentsByStudentUseCase = listEnrollmentsByStudentUseCase;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Valid @RequestBody CreateEnrollmentRequest request) {
        var command = new CreateEnrollmentCommand(request.studentId(), request.courseId());
        var enrollment = createEnrollmentUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(new EnrollmentResponse(
                enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId(),
                enrollment.getStatus(), enrollment.getEnrolledAt(),
                enrollment.getCompletedAt(), enrollment.getCancelledAt()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> findById(@PathVariable UUID id) {
        var readModel = findEnrollmentByIdUseCase.execute(id);
        return ResponseEntity.ok(EnrollmentResponse.fromReadModel(readModel));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentSummaryResponse>> listByStudent(@PathVariable UUID studentId) {
        List<EnrollmentSummaryResponse> result = listEnrollmentsByStudentUseCase.execute(studentId).stream()
                .map(EnrollmentSummaryResponse::fromReadModel)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentSummaryResponse>> listByCourse(@PathVariable UUID courseId) {
        List<EnrollmentSummaryResponse> result = listEnrollmentsByCourseUseCase.execute(courseId).stream()
                .map(EnrollmentSummaryResponse::fromReadModel)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable UUID id) {
        completeEnrollmentUseCase.execute(new EnrollmentIdCommand(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        cancelEnrollmentUseCase.execute(new EnrollmentIdCommand(id));
        return ResponseEntity.noContent().build();
    }
}