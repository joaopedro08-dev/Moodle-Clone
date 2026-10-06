package moodle_clone.backend.adapter.in.web.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateAddressRequest(
        @NotBlank String street,
        String number,
        @NotBlank String city,
        String state,
        String zipCode,
        String country
) {}