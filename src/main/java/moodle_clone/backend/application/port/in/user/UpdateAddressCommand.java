package moodle_clone.backend.application.port.in.user;

import java.util.UUID;

public record UpdateAddressCommand(
        UUID userId,
        String street,
        String number,
        String city,
        String state,
        String zipCode
) {}