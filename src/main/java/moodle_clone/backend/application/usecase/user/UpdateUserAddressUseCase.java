package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.in.user.UpdateAddressCommand;
import moodle_clone.backend.application.port.out.user.UserRepositoryPort;
import moodle_clone.backend.domain.model.user.Address;
import moodle_clone.backend.domain.model.user.User;

public class UpdateUserAddressUseCase {

    private final UserRepositoryPort repository;

    public UpdateUserAddressUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public User execute(UpdateAddressCommand command) {
        User user = repository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(command.userId()));

        Address newAddress = new Address(
                command.street(),
                command.number(),
                command.city(),
                command.state(),
                command.zipCode()
        );

        user.updateAddress(newAddress);

        return repository.save(user);
    }
}