package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.exception.NotFoundException;
import moodle_clone.backend.application.port.out.user.UserQueryPort;
import moodle_clone.backend.application.readmodel.user.UserReadModel;

import java.util.UUID;

public class FindUserByIdUseCase {

    private final UserQueryPort queryPort;

    public FindUserByIdUseCase(UserQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public UserReadModel execute(UUID userId) {
        return queryPort.findReadModelById(userId)
                .orElseThrow(() -> new NotFoundException(userId));
    }
}