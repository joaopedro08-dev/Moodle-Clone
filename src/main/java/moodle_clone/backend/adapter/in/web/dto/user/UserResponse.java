package moodle_clone.backend.adapter.in.web.dto.user;

import moodle_clone.backend.application.readmodel.user.UserReadModel;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Set<String> roles,
        boolean isActive,
        LocalDate dateBirth
) {
    public static UserResponse fromReadModel(UserReadModel rm) {
        return new UserResponse(
                rm.id(), rm.name(), rm.email(),
                rm.roles().stream().map(Enum::name).collect(java.util.stream.Collectors.toSet()),
                rm.isActive(), rm.dateBirth()
        );
    }
}