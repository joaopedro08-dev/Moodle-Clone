package moodle_clone.backend.adapter.out.persistence.adapter.user;

import moodle_clone.backend.application.port.out.user.UserQueryPort;
import moodle_clone.backend.application.readmodel.user.UserReadModel;
import moodle_clone.backend.application.readmodel.user.UserSummaryReadModel;
import moodle_clone.backend.domain.model.enums.user.Nationality;
import moodle_clone.backend.domain.model.enums.user.Roles;
import moodle_clone.backend.adapter.out.persistence.entity.user.UserJpaEntity;
import moodle_clone.backend.adapter.out.persistence.repository.user.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserQueryAdapter implements UserQueryPort {

    private final UserJpaRepository jpaRepository;

    public UserQueryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<UserReadModel> findReadModelById(UUID id) {
        return jpaRepository.findById(id).map(this::toReadModel);
    }

    @Override
    public List<UserSummaryReadModel> findAllSummaries() {
        return jpaRepository.findAll().stream()
                .map(e -> new UserSummaryReadModel(e.getId(), e.getName(), e.getEmail(), e.isActive()))
                .collect(Collectors.toList());
    }

    private UserReadModel toReadModel(UserJpaEntity e) {
        Set<Roles> roles = e.getRoles().stream().map(Roles::valueOf).collect(Collectors.toSet());
        Nationality nationality = e.getNationality() != null ? Nationality.valueOf(e.getNationality()) : null;

        return new UserReadModel(
                e.getId(), e.getName(), e.getEmail(), roles, e.isActive(), e.getDateBirth(),
                nationality, e.getStreet(), e.getNumber(), e.getCity(), e.getState(),
                e.getZipCode(), e.getPhoneNumber()
        );
    }
}