package br.com.gastrovision.api.repositories;
import br.com.gastrovision.api.entity.User;
import java.util.List;  
import java.util.Optional;


public interface UserRepository {
    // Aqui você pode adicionar métodos personalizados de consulta, se necessário

    List<User> findAll(int size, int offset);

    Integer save(User user);

    Integer delete(String userId);

    Optional<User> findById(String userId);

    Integer update(String userId, User user);
    // adiciona service para buscar pelo nome 
    List<User> findByName(String name);

}