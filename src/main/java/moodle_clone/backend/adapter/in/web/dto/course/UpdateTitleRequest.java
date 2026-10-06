package moodle_clone.backend.adapter.in.web.dto.course;

import jakarta.validation.constraints.NotBlank;

public record UpdateTitleRequest(
        @NotBlank(message = "O título não pode ser vazio") String title
) {}