package moodle_clone.backend.application.usecase.user;

import moodle_clone.backend.application.port.out.user.UserQueryPort;
import moodle_clone.backend.application.readmodel.user.UserSummaryReadModel;

import java.util.List;

public class ListUsersUseCase {

    private final UserQueryPort queryPort;

    public ListUsersUseCase(UserQueryPort queryPort) {
        this.queryPort = queryPort;
    }

    public List<UserSummaryReadModel> execute() {
        return queryPort.findAllSummaries();
    }
}