package moodle_clone.backend.domain.exception.enrollment;

public class InvalidEnrollmentException extends RuntimeException {
    public InvalidEnrollmentException(String message) {
        super(message);
    }
}
