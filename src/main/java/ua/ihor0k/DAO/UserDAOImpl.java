package ua.ihor0k.DAO;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.ihor0k.model.User;

@Repository
@Slf4j //TODO logging
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Override
    public void createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session
                .createQuery("from User where username =: username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
