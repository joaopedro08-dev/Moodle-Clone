package moodle_clone.backend.application.port.out.user;

import moodle_clone.backend.domain.model.user.Email;
import moodle_clone.backend.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);
}