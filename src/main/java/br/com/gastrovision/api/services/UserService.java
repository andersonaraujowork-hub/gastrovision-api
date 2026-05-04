package br.com.gastrovision.api.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

      public List<User> findAllUsers(int page, int size){
        int offset =(page - 1)  * size;
        return this.userRepository.findAll(size, offset);
    }

    public void saveUser(User user){
        var save = this.userRepository.save(user);
        Assert.state( save == 1,  "Erro ao salvar usuário "+user.getName());
    }

    public void deleteUser(String userId){
        var delete = this.userRepository.delete(userId);
        Assert.state( delete == 1,  "Erro ao deletar usuário "+userId);
    }

    public Optional<User> findById(String userId){
        return this.userRepository.findById(userId);
    }

    public void updateUser(String userId, User user){
        var updated = this.userRepository.update(userId, user);
        Assert.state(updated == 1, "Erro ao atualizar usuário " + userId);
    }
    // Service para buscar usuario pelo nome
    public List<User> findByName(String name) {
        return this.userRepository.findByName(name);
    }
}
