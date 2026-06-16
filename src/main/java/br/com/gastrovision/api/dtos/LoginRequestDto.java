package br.com.gastrovision.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "O login não pode ser vazio")
        String login,

        @NotBlank(message = "A senha não pode ser vazia")
        String password
) {}

