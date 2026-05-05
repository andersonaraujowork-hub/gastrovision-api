package br.com.gastrovision.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gastrovision.api.dtos.LoginRequestDto;
import br.com.gastrovision.api.dtos.PasswordUpdateDto;
import br.com.gastrovision.api.entity.User;
import br.com.gastrovision.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar usuários", description = "Retorna lista paginada de usuários")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        List<User> users = userService.findAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Criar usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário pelo seu identificador único")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") String userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar usuário", description = "Remove um usuário pelo seu identificador único")
    @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados do usuário exceto a senha")
    @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PutMapping("/{userid}")
    public ResponseEntity<Void> updateUser(@PathVariable("userid") String userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários por nome", description = "Retorna usuários cujo nome contenha o termo informado")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/search")
    public ResponseEntity<List<User>> findByName(@RequestParam("name") String name) {
        List<User> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Validar login", description = "Verifica se login e senha são válidos")
    @ApiResponse(responseCode = "200", description = "Login válido")
    @ApiResponse(responseCode = "401", description = "Login ou senha inválidos")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequestDto loginRequest) {
        return userService.login(loginRequest.login(), loginRequest.password())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @Operation(summary = "Trocar senha", description = "Atualiza a senha do usuário em endpoint exclusivo")
    @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @PatchMapping("/{userid}/password")
    public ResponseEntity<Void> updatePassword(
        @PathVariable("userid") String userId,
        @RequestBody PasswordUpdateDto dto) {
        userService.updatePassword(userId, dto);
        return ResponseEntity.noContent().build();
    }
}
