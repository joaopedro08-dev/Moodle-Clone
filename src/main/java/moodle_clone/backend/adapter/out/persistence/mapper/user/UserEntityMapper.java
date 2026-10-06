package moodle_clone.backend.adapter.out.persistence.mapper.user;

import moodle_clone.backend.domain.model.enums.user.Nationality;
import moodle_clone.backend.domain.model.enums.user.Roles;
import moodle_clone.backend.domain.model.user.*;
import moodle_clone.backend.adapter.out.persistence.entity.user.UserJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserEntityMapper {

    public static UserJpaEntity toJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail().getValue());
        entity.setRoles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        entity.setActive(user.isActive());
        entity.setDateBirth(user.getDateBirth());
        entity.setNationality(user.getNationality() != null ? user.getNationality().name() : null);

        Address address = user.getAddress();
        if (address != null) {
            entity.setStreet(address.getStreet());
            entity.setNumber(address.getNumber());
            entity.setCity(address.getCity());
            entity.setState(address.getState() != null ? address.getState() : null);
            entity.setZipCode(address.getZipCode());
        }

        Phone phone = user.getPhone();
        entity.setPhoneNumber(phone != null ? phone.getNumber() : null);

        return entity;
    }

    public static User toDomain(UserJpaEntity entity) {
        Set<Roles> roles = entity.getRoles().stream()
                .map(Roles::valueOf)
                .collect(Collectors.toSet());

        Nationality nationality = entity.getNationality() != null
                ? Nationality.valueOf(entity.getNationality())
                : null;

        Address address = entity.getStreet() != null
                ? new Address(
                entity.getStreet(),
                entity.getNumber(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode()
        )
                : null;

        Phone phone = entity.getPhoneNumber() != null
                ? new Phone(entity.getPhoneNumber())
                : null;

        return User.reconstruct(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getName(),
                new Email(entity.getEmail()),
                roles,
                entity.isActive(),
                entity.getDateBirth(),
                nationality,
                phone,
                address
        );
    }
}