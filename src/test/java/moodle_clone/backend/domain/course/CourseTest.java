package moodle_clone.backend.domain.course;

import moodle_clone.backend.domain.exception.course.InvalidCourseException;
import moodle_clone.backend.domain.model.course.Course;
import moodle_clone.backend.domain.model.enums.course.CourseCategory;
import moodle_clone.backend.domain.model.enums.course.CourseStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @Test
    void shouldCreateCourseAsDraft() {
        UUID instructor = UUID.randomUUID();

        Course course = course(instructor);

        assertEquals("Java", course.getTitle());
        assertEquals(CourseStatus.DRAFT, course.getStatus());
        assertEquals(Set.of(instructor), course.getInstructors());
    }

    @Test
    void shouldRejectInvalidCourseData() {
        UUID instructor = UUID.randomUUID();

        assertThrows(InvalidCourseException.class,
                () -> new Course(UUID.randomUUID(), " ", "description", "JAVA",
                        Set.of(instructor), 40, 10));
        assertThrows(InvalidCourseException.class,
                () -> new Course(UUID.randomUUID(), "Java", "description", "",
                        Set.of(instructor), 40, 10));
        assertThrows(InvalidCourseException.class,
                () -> new Course(UUID.randomUUID(), "Java", "description", "JAVA",
                        Set.of(), 40, 10));
        assertThrows(InvalidCourseException.class,
                () -> new Course(UUID.randomUUID(), "Java", "description", "JAVA",
                        Set.of(instructor), 0, 10));
        assertThrows(InvalidCourseException.class,
                () -> new Course(UUID.randomUUID(), "Java", "description", "JAVA",
                        Set.of(instructor), 40, 0));
    }

    @Test
    void shouldPublishAndArchiveCourse() {
        Course course = course(UUID.randomUUID());

        course.publish();
        course.archive();

        assertEquals(CourseStatus.ARCHIVED, course.getStatus());
        assertThrows(IllegalStateException.class, course::publish);
        assertThrows(IllegalStateException.class, course::archive);
    }

    @Test
    void shouldManageInstructors() {
        UUID firstInstructor = UUID.randomUUID();
        UUID secondInstructor = UUID.randomUUID();
        Course course = course(firstInstructor);

        course.addInstructor(secondInstructor);
        course.removeInstructor(firstInstructor);

        assertEquals(Set.of(secondInstructor), course.getInstructors());
        assertThrows(IllegalStateException.class, () -> course.removeInstructor(secondInstructor));
    }

    @Test
    void shouldUpdateCourseDetails() {
        Course course = course(UUID.randomUUID());

        course.updateTitle("Spring");
        course.updateDescription("Updated");
        course.updateWorkload(80);
        course.updateMaxEnrollments(20);
        course.updatePeriod(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 6, 30));
        course.updateCategory(CourseCategory.TECHNOLOGY);

        assertEquals("Spring", course.getTitle());
        assertEquals("Updated", course.getDescription());
        assertEquals(80, course.getWorkload());
        assertEquals(20, course.getMaxEnrollments());
        assertEquals(LocalDate.of(2026, 1, 1), course.getStartDate());
        assertEquals(LocalDate.of(2026, 6, 30), course.getEndDate());
        assertEquals(CourseCategory.TECHNOLOGY, course.getCategory());
    }

    @Test
    void shouldRejectInvalidUpdates() {
        Course course = course(UUID.randomUUID());

        assertThrows(InvalidCourseException.class, () -> course.updateTitle(""));
        assertThrows(InvalidCourseException.class, () -> course.updateWorkload(0));
        assertThrows(InvalidCourseException.class, () -> course.updateMaxEnrollments(0));
        assertThrows(InvalidCourseException.class,
                () -> course.updatePeriod(LocalDate.of(2026, 2, 1), LocalDate.of(2026, 1, 1)));
    }

    @Test
    void shouldNotModifyArchivedCourse() {
        Course course = course(UUID.randomUUID());
        course.archive();

        assertThrows(IllegalStateException.class, () -> course.updateTitle("New title"));
        assertThrows(IllegalStateException.class, () -> course.updateDescription("New description"));
        assertThrows(IllegalStateException.class, () -> course.addInstructor(UUID.randomUUID()));
    }

    private Course course(UUID instructor) {
        return new Course(UUID.randomUUID(), "Java", "description", "JAVA",
                Set.of(instructor), 40, 10);
    }
}
