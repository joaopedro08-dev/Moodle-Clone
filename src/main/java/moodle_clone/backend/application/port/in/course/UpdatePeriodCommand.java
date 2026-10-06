package moodle_clone.backend.application.port.in.course;

import java.time.LocalDate;
import java.util.UUID;

public record UpdatePeriodCommand(UUID courseId, LocalDate startDate, LocalDate endDate) {}