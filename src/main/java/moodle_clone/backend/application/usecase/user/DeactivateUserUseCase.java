package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.User;

import java.util.UUID;

public class DeactivateUserUseCase {

    private final UserRepositoryPort repository;

    public DeactivateUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public void execute(UUID userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));

        user.deactivate();

        repository.save(user);
    }
}