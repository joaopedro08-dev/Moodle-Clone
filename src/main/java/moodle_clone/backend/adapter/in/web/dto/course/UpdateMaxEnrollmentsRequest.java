package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.Positive;

public record UpdateMaxEnrollmentsRequest(
        @Positive(message = "O limite de vagas deve ser maior que zero") int maxEnrollments
) {}