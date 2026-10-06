package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateDateBirthRequest(
        @NotNull LocalDate dateBirth
) {}