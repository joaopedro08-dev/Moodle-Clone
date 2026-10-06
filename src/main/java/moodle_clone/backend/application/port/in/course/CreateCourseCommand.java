package moodle_clone.backend.application.port.in.course;

import java.util.Set;
import java.util.UUID;

public record CreateCourseCommand(
        String title,
        String description,
        String code,
        Set<UUID> instructors,
        int workload,
        int maxEnrollments
) {}