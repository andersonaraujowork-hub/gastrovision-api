package br.com.gastrovision.api.repositories;
import br.com.gastrovision.api.entity.User;
import java.util.List;


public interface UserRepository {
    // Aqui você pode adicionar métodos personalizados de consulta, se necessário

    List<User> findAll(int size, int offset);

     Integer save(User user);

}