package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.out.course.CourseQueryPort;
import moodle_clone.backend.application.readmodel.course.CourseReadModel;

import java.util.UUID;

public class FindCourseByIdUseCase {

    private final CourseQueryPort queryPort;

    public FindCourseByIdUseCase(CourseQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public CourseReadModel execute(UUID courseId) {
        return queryPort.findReadModelById(courseId)
                .orElseThrow(() -> new NotFoundException(courseId));
    }
}