package br.com.gastrovision.api.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.gastrovision.api.dtos.AddressDto;
import br.com.gastrovision.api.dtos.PasswordUpdateDto;
import br.com.gastrovision.api.dtos.UserRequestDto;
import br.com.gastrovision.api.dtos.UserResponseDto;
import br.com.gastrovision.api.entity.Address;
import br.com.gastrovision.api.entity.User;
import br.com.gastrovision.api.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> findAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        return this.userRepository.findAll(size, offset)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void saveUser(UserRequestDto dto) {
        var save = this.userRepository.save(toEntity(dto));
        Assert.state(save == 1, "Erro ao salvar usuário " + dto.name());
    }

    public void deleteUser(String userId) {
        var delete = this.userRepository.delete(userId);
        Assert.state(delete == 1, "Erro ao deletar usuário " + userId);
    }

    public Optional<UserResponseDto> findById(String userId) {
        return this.userRepository.findById(userId).map(this::toResponse);
    }

    public void updateUser(String userId, UserRequestDto dto) {
        var updated = this.userRepository.update(userId, toEntity(dto));
        Assert.state(updated == 1, "Erro ao atualizar usuário " + userId);
    }

    public List<UserResponseDto> findByName(String name) {
        return this.userRepository.findByName(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<UserResponseDto> login(String login, String password) {
        return this.userRepository.findByLoginAndPassword(login, password)
                .map(this::toResponse);
    }

    public void updatePassword(String userId, PasswordUpdateDto dto) {
        var updated = userRepository.updatePassword(userId, dto.newPassword());
        Assert.state(updated == 1, "Erro ao atualizar senha do usuário " + userId);
    }

    private UserResponseDto toResponse(User user) {
        var addr = user.getAddress();
        var addressDto = new AddressDto(
                addr.getStreet(),
                addr.getNumber(),
                addr.getComplement(),
                addr.getNeighborhood(),
                addr.getCity(),
                addr.getState(),
                addr.getZipCode()
        );
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                addressDto,
                user.getUserType(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    private User toEntity(UserRequestDto dto) {
        var address = new Address(
                dto.address().street(),
                dto.address().number(),
                dto.address().complement(),
                dto.address().neighborhood(),
                dto.address().city(),
                dto.address().state(),
                dto.address().zipCode()
        );
        var user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        user.setAddress(address);
        user.setUserType(dto.userType());
        return user;
    }
}
