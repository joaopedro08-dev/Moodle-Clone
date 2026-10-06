package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.AddRoleCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.User;

public class AddRoleToUserUseCase {

    private final UserRepositoryPort repository;

    public AddRoleToUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(AddRoleCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        user.addRole(command.role());

        return repository.save(user);
    }
}