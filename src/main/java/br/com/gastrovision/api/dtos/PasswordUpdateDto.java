package br.com.gastrovision.api.dtos;
import jakarta.validation.constraints.NotBlank;

public record PasswordUpdateDto(
    @NotBlank String newPassword
) {}
