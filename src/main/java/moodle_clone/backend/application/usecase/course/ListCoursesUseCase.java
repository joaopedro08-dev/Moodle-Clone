package moodle_clone.backend.application.usecase.course;

import moodle_clone.backend.application.port.out.course.CourseQueryPort;
import moodle_clone.backend.application.readmodel.course.CourseSummaryReadModel;

import java.util.List;

public class ListCoursesUseCase {

    private final CourseQueryPort queryPort;

    public ListCoursesUseCase(CourseQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public List<CourseSummaryReadModel> execute() {
        return queryPort.findAllSummaries();
    }
}