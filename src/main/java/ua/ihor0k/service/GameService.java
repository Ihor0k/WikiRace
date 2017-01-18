package ua.ihor0k.service;

import ua.ihor0k.model.Game;
import ua.ihor0k.model.User;

import java.util.List;

public interface GameService {
    void createGame(Game game);

    Game getGame(int id);

    List<Game> getAllGames();

    List<Game> getGamesByUser(User user);
}
