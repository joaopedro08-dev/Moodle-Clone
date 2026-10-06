package moodle_clone.backend.application.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Recurso não encontrado: " + id);
    }

    public NotFoundException(String message) {
        super(message);
    }
}