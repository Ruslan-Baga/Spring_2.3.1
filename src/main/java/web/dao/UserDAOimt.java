package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;


@Component
public class UserDAOimt {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<User> allUsers(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    public void save(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }

    @Transactional
    public void update(User userUpg, int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null) {
            user.setName(userUpg.getName());
            user.setAge(userUpg.getAge());
            user.setEmail(userUpg.getEmail());
        }
    }
    @Transactional(readOnly = true)
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }


}

