package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.Positive;

public record UpdateWorkloadRequest(
        @Positive(message = "A carga horária deve ser maior que zero") int workload
) {}