package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Set;
import java.util.UUID;

public record CreateCourseRequest(
        @NotBlank(message = "O título não pode ser vazio") String title,
        String description,
        @NotBlank(message = "O código não pode ser vazio") String code,
        @NotEmpty(message = "O curso precisa de ao menos um instrutor") Set<UUID> instructors,
        @Positive(message = "A carga horária deve ser maior que zero") int workload,
        @Positive(message = "O limite de vagas deve ser maior que zero") int maxEnrollments
) {}