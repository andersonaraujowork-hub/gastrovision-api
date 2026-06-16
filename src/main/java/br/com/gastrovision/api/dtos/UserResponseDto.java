package br.com.gastrovision.api.dtos;

import br.com.gastrovision.api.entity.UserType;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        String login,
        AddressDto address,
        UserType userType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
