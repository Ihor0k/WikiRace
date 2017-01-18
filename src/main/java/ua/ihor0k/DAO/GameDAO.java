package ua.ihor0k.DAO;

import ua.ihor0k.model.Game;
import ua.ihor0k.model.User;

import java.util.List;

public interface GameDAO {
    void createGame(Game game);

    Game getGame(int id);

    List<Game> getAllGames();

    List<Game> getGamesByUser(User user);
}
