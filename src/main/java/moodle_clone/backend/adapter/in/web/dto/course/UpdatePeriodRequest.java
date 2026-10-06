package moodle_clone.backend.adapter.in.web.dto.course;

import java.time.LocalDate;

public record UpdatePeriodRequest(LocalDate startDate, LocalDate endDate) {}