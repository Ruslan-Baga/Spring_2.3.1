package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(int id);

    void updateUser(User User, int id);

    User getUserById(int id);
}
