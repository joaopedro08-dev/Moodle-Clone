package moodle_clone.backend.application.port.out.user;

import moodle_clone.backend.application.readmodel.user.UserReadModel;
import moodle_clone.backend.application.readmodel.user.UserSummaryReadModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserQueryPort {

    Optional<UserReadModel> findReadModelById(UUID id);

    List<UserSummaryReadModel> findAllSummaries();
}