package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdateNameCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserNameUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserNameUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public User execute(UpdateNameCommand command) {
        User user = userRepositoryPort.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        user.updateName(command.name());

        return userRepositoryPort.save(user);
    }
}