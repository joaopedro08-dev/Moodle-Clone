package moodle_clone.backend.application.readmodel.user;

import moodle_clone.backend.domain.model.enums.user.Nationality;
import moodle_clone.backend.domain.model.enums.user.Roles;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UserReadModel(
        UUID id,
        String name,
        String email,
        Set<Roles> roles,
        boolean isActive,
        LocalDate dateBirth,
        Nationality nationality,
        String street,
        String number,
        String city,
        String state,
        String zipCode,
        String phoneNumber
) {}