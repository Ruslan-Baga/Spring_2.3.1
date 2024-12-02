package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOimp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void updateUser(User userUpg, int id) {
        User user = entityManager.find(User.class, id);

        user.setName(userUpg.getName());
        user.setAge(userUpg.getAge());
        user.setEmail(userUpg.getEmail());
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
