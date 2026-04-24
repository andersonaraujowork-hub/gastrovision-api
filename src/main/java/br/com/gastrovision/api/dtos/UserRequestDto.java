package br.com.gastrovision.api.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import br.com.gastrovision.api.entity.UserType;


public record UserRequestDto(
        @NotBlank(message = "O nome não pode ser vazio")
        String name,
        @NotBlank(message = "O email não pode ser vazio")
        String email,
        @NotBlank(message = "A senha não pode ser vazio")
        String password,
        @NotBlank(message = "O login não pode ser vazio")
        String login,
        @NotNull
        AddressDto address,
        @NotNull(message = "O tipo de usuário não pode ser nulo")
        UserType userType

) {
}