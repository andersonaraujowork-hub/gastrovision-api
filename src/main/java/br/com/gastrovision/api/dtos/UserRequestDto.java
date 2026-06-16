package br.com.gastrovision.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import br.com.gastrovision.api.entity.UserType;

public record UserRequestDto(
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "O email deve ter um formato válido")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        String password,

        @NotBlank(message = "O login não pode ser vazio")
        String login,

        @NotNull(message = "O endereço não pode ser nulo")
        @Valid
        AddressDto address,

        @NotNull(message = "O tipo de usuário não pode ser nulo")
        UserType userType
) {}
