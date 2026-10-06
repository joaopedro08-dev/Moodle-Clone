package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdateEmailCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.Email;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserEmailUseCase {

    private final UserRepositoryPort repository;

    public UpdateUserEmailUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(UpdateEmailCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        Email email = new Email(command.email());

        user.updateEmail(email);

        return repository.save(user);
    }
}
