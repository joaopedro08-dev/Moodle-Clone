package moodle_clone.backend.domain.enrollment;

import moodle_clone.backend.domain.exception.enrollment.InvalidEnrollmentException;
import moodle_clone.backend.domain.model.enrollment.Enrollment;
import moodle_clone.backend.domain.model.enums.enrollment.EnrollmentStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnrollmentTest {

    @Test
    void shouldCreateActiveEnrollment() {
        Enrollment enrollment = enrollment();

        assertEquals(EnrollmentStatus.ACTIVE, enrollment.getStatus());
        assertTrue(enrollment.isActive());
        assertEquals(LocalDate.now(), enrollment.getEnrolledAt());
        assertNull(enrollment.getCompletedAt());
        assertNull(enrollment.getCancelledAt());
    }

    @Test
    void shouldRejectMissingStudentOrCourse() {
        assertThrows(InvalidEnrollmentException.class,
                () -> new Enrollment(UUID.randomUUID(), null, UUID.randomUUID()));
        assertThrows(InvalidEnrollmentException.class,
                () -> new Enrollment(UUID.randomUUID(), UUID.randomUUID(), null));
    }

    @Test
    void shouldCompleteActiveEnrollment() {
        Enrollment enrollment = enrollment();

        enrollment.complete();

        assertEquals(EnrollmentStatus.COMPLETED, enrollment.getStatus());
        assertFalse(enrollment.isActive());
        assertEquals(LocalDate.now(), enrollment.getCompletedAt());
        assertNull(enrollment.getCancelledAt());
        assertThrows(IllegalStateException.class, enrollment::complete);
        assertThrows(IllegalStateException.class, enrollment::cancel);
    }

    @Test
    void shouldCancelActiveEnrollment() {
        Enrollment enrollment = enrollment();

        enrollment.cancel();

        assertEquals(EnrollmentStatus.CANCELLED, enrollment.getStatus());
        assertFalse(enrollment.isActive());
        assertEquals(LocalDate.now(), enrollment.getCancelledAt());
        assertNull(enrollment.getCompletedAt());
        assertThrows(IllegalStateException.class, enrollment::cancel);
        assertThrows(IllegalStateException.class, enrollment::complete);
    }

    private Enrollment enrollment() {
        return new Enrollment(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }
}
