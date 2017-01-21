package ua.ihor0k.DAO;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.User;

import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO {
    private SessionFactory sessionFactory;

    @Override
    public void createGame(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(game);
    }

    @Override
    public Game getGame(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Game.class, id);
    }

    @Override
    public List<Game> getAllGames() {
        Session session = sessionFactory.getCurrentSession();
        List<Game> list = session
                .createQuery("from Game ", Game.class)
                .list();
        return list;
    }

    @Override
    public List<Game> getGamesByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<Game> list = session
                .createQuery("from Game where user = :userId", Game.class)
                .setParameter("userId", user.getId())
                .list();
        return list;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
