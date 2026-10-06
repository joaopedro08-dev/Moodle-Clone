package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdatePhoneRequest(
        @NotBlank String phoneNumber
) {}