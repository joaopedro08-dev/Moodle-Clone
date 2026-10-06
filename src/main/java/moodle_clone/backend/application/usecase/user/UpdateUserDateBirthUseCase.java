package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdateDateBirthCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserDateBirthUseCase {

    private final UserRepositoryPort repository;

    public UpdateUserDateBirthUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(UpdateDateBirthCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        user.updateDateBirth(command.dateBirth());

        return repository.save(user);
    }
}