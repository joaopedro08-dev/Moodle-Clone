package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InstructorRequest(
        @NotNull(message = "O id do instrutor é obrigatório") UUID instructorId
) {}