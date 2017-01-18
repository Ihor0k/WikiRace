package ua.ihor0k.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ihor0k.DAO.GameDAO;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.User;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameDAO gameDAO;

    @Override
    @Transactional
    public void createGame(Game game) {
        gameDAO.createGame(game);
    }

    @Override
    @Transactional
    public Game getGame(int id) {
        return gameDAO.getGame(id);
    }

    @Override
    @Transactional
    public List<Game> getAllGames() {
        return gameDAO.getAllGames();
    }

    @Override
    @Transactional
    public List<Game> getGamesByUser(User user) {
        return gameDAO.getGamesByUser(user);
    }

    @Autowired
    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }
}
