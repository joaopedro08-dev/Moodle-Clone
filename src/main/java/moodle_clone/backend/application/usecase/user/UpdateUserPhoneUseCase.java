package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdatePhoneCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.Phone;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserPhoneUseCase {

    private final UserRepositoryPort repository;

    public UpdateUserPhoneUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(UpdatePhoneCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        Phone newPhone = new Phone(command.phoneNumber());

        user.updatePhone(newPhone);

        return repository.save(user);
    }
}