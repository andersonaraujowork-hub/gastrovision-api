package br.com.gastrovision.api.dtos;

public record LoginRequestDto(
        String login,
        String password
) {
}
