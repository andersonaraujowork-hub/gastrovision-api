package br.com.gastrovision.api.repositories;
import br.com.gastrovision.api.entity.Address;
import br.com.gastrovision.api.entity.User;
import br.com.gastrovision.api.entity.UserType;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public class UserRespositoryImp implements UserRepository {
    private final JdbcClient jdbcClient;

     public UserRespositoryImp(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
     }

        @Override
        public List<User> findAll(int size, int offset) {
            return this.jdbcClient
            .sql("SELECT * FROM tb_users LIMIT :size OFFSET :offset")
            .param("size", size)
            .param("offset", offset)
            .query((rs, rowNum) -> {
                var address = new Address();
                address.setStreet(rs.getString("street"));
                address.setNumber(rs.getString("number"));
                address.setComplement(rs.getString("complement"));
                address.setNeighborhood(rs.getString("neighborhood"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setZipCode(rs.getString("zip_code"));

                var user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserType(UserType.valueOf(rs.getString("user_type")));
                user.setAddress(address);
                user.setCreatedAt(rs.getObject("created_at", java.time.LocalDateTime.class));
                user.setUpdatedAt(rs.getObject("updated_at", java.time.LocalDateTime.class));
                return user;
            })
            .list();
        }

        @Override
        public Integer save(User user) {
        return this.jdbcClient
                .sql("INSERT INTO tb_users (id, name, email, login, password, user_type, street, number, complement, neighborhood, city, state, zip_code, created_at, updated_at) VALUES(:id, :name, :email, :login, :password, :userType, :street, :number, :complement, :neighborhood, :city, :state, :zipCode, :createdAt, :updatedAt)")
                .param("id", UUID.randomUUID().toString())
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .param("userType", user.getUserType().name())
                .param("street", user.getAddress().getStreet())
                .param("number", user.getAddress().getNumber())
                .param("complement", user.getAddress().getComplement())
                .param("neighborhood", user.getAddress().getNeighborhood())
                .param("city", user.getAddress().getCity())
                .param("state", user.getAddress().getState())
                .param("zipCode", user.getAddress().getZipCode())
                .param("createdAt", java.time.LocalDateTime.now())
                .param("updatedAt", java.time.LocalDateTime.now())
                .update();
    }

    @Override
    public Integer delete(String userId) {
        return this.jdbcClient
            .sql("DELETE FROM tb_users WHERE id = :id")
            .param("id", userId)
            .update();
    }

    @Override
    public Integer update(String userId, User user) {
        return this.jdbcClient
                .sql("UPDATE tb_users SET name = :name, email = :email, login = :login, password = :password, user_type = :userType, street = :street, number = :number, complement = :complement, neighborhood = :neighborhood, city = :city, state = :state, zip_code = :zipCode, updated_at = :updatedAt WHERE id = :id")
                .param("id", userId)
                .param("name", user.getName())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("password", user.getPassword())
                .param("userType", user.getUserType().name())
                .param("street", user.getAddress().getStreet())
                .param("number", user.getAddress().getNumber())
                .param("complement", user.getAddress().getComplement())
                .param("neighborhood", user.getAddress().getNeighborhood())
                .param("city", user.getAddress().getCity())
                .param("state", user.getAddress().getState())
                .param("zipCode", user.getAddress().getZipCode())
                .param("updatedAt", java.time.LocalDateTime.now())
                .update();
    }

    @Override
    public Optional<User> findById(String userId){
        return this.jdbcClient.sql("SELECT * FROM tb_users WHERE id = :id")
        .param("id", userId)
        .query((rs) -> {
            if (rs.next()) {
                var address = new Address();
                address.setStreet(rs.getString("street"));
                address.setNumber(rs.getString("number"));
                address.setComplement(rs.getString("complement"));
                address.setNeighborhood(rs.getString("neighborhood"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setZipCode(rs.getString("zip_code"));

                var user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserType(UserType.valueOf(rs.getString("user_type")));
                user.setAddress(address);
                user.setCreatedAt(rs.getObject("created_at", java.time.LocalDateTime.class));
                user.setUpdatedAt(rs.getObject("updated_at", java.time.LocalDateTime.class));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });
    }


    
    @Override
    public List<User> findByName(String name) {
        return this.jdbcClient
            .sql("SELECT * FROM tb_users WHERE name LIKE CONCAT('%', :name, '%')")
            .param("name", name)
            .query((rs, rowNum) -> {
                var address = new Address();
                address.setStreet(rs.getString("street"));
                address.setNumber(rs.getString("number"));
                address.setComplement(rs.getString("complement"));
                address.setNeighborhood(rs.getString("neighborhood"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setZipCode(rs.getString("zip_code"));

                var user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setUserType(UserType.valueOf(rs.getString("user_type")));
                user.setAddress(address);
                user.setCreatedAt(rs.getObject("created_at", java.time.LocalDateTime.class));
                user.setUpdatedAt(rs.getObject("updated_at", java.time.LocalDateTime.class));
            return user;
        })
        .list();
}


}
