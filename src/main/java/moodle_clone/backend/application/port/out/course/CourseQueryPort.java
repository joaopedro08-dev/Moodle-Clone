package moodle_clone.backend.application.port.out.course;

import moodle_clone.backend.application.readmodel.course.CourseReadModel;
import moodle_clone.backend.application.readmodel.course.CourseSummaryReadModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseQueryPort {

    Optional<CourseReadModel> findReadModelById(UUID id);

    List<CourseSummaryReadModel> findAllSummaries();

    List<CourseSummaryReadModel> findByInstructor(UUID instructorId);
}
