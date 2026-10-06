package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdateNationalityCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserNationalityUseCase {

    private final UserRepositoryPort repository;

    public UpdateUserNationalityUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(UpdateNationalityCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        user.updateNationality(command.nationality());

        return repository.save(user);
    }
}