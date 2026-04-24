package br.com.gastrovision.api.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.gastrovision.api.dtos.UserRequestDto;
import br.com.gastrovision.api.entity.Address;
import br.com.gastrovision.api.entity.User;
import br.com.gastrovision.api.repositories.UserRepository;
import java.util.List;

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

     
}
