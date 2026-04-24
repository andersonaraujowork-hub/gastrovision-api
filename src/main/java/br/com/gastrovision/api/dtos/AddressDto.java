package br.com.gastrovision.api.dtos;
import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank(message = "O logradouro não pode ser nulo")
        String street,
        @NotBlank(message = "O número não pode ser nulo")
        String number,
        String complement,
        @NotBlank(message = "O bairro não pode ser nulo")
        String neighborhood,
        @NotBlank(message = "A cidade não pode ser nula")
        String city,
        @NotBlank(message = "O estado não pode ser nulo")
        String state,
        @NotBlank(message = "O cep não pode ser nulo")
        String zipCode
) {
}
