package moodle_clone.backend.adapter.in.web.dto.user;

import moodle_clone.backend.application.readmodel.user.UserSummaryReadModel;

import java.util.UUID;

public record UserSummaryResponse(UUID id, String name, String email, boolean isActive) {
    public static UserSummaryResponse fromReadModel(UserSummaryReadModel rm) {
        return new UserSummaryResponse(rm.id(), rm.name(), rm.email(), rm.isActive());
    }
}