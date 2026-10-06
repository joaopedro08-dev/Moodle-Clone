package moodle_clone.backend.adapter.in.web.dto.enrollment;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateEnrollmentRequest(
        @NotNull(message = "O id do aluno é obrigatório") UUID studentId,
        @NotNull(message = "O id do curso é obrigatório") UUID courseId
) {}