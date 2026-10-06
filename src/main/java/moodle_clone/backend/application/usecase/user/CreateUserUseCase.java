package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.ConflictException;
import moodle_clone.backend.application.port.in.user.CreateUserCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.Email;
import moodle_clone.backend.domain.model.user.User;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepositoryPort repository;

    public CreateUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(CreateUserCommand command) {
        Email email = new Email(command.email());

        if (repository.existsByEmail(email)) {
            throw new ConflictException(command.email());
        }

        User user = new User(
                UUID.randomUUID(),
                command.name(),
                email,
                command.role()
        );

        return repository.save(user);
    }
}