import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.ihor0k.config.ApplicationConfig;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.Page;
import ua.ihor0k.model.User;
import ua.ihor0k.service.GameService;
import ua.ihor0k.service.UserService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DatabaseTest {
    private UserService userService;
    private GameService gameService;

    @Test
    @Rollback
    @Transactional
    public void userTest() {
        User user = new User("username", "password");

        userService.createUser(user);

        User newUser = (User) userService.loadUserByUsername("username");

        Assert.assertEquals(user, newUser);
    }

    @Test
    @Rollback
    @Transactional
    public void gameTest() {
        User user = new User("username", "password");
        Game game = makeDummyGame();
        game.setUser(user);

        gameService.createGame(game);

        Game newGame1 = gameService.getGamesByUser(user).get(0);
        Game newGame2 = gameService.getGame(game.getId());

        Assert.assertEquals(game, newGame1);
        Assert.assertEquals(game, newGame2);
        Assert.assertEquals(user, newGame1.getUser());
    }

    @Test
    @Rollback
    @Transactional
    public void userWithGamesTest() {
        User user = new User("username", "password");

        List<Game> games = Arrays.asList(makeDummyGame(), makeDummyGame(), makeDummyGame());
        games.forEach(user::addGame);

        userService.createUser(user);

        User newUser = (User) userService.loadUserByUsername("username");

        Assert.assertEquals(user, newUser);
    }

    @Test
    @Rollback
    @Transactional
    public void gamesWithTheSameUser() {
        User user = new User("username", "password");

        List<Game> games = Arrays.asList(makeDummyGame(), makeDummyGame(), makeDummyGame());
        games.forEach(game -> game.setUser(user));
        games.forEach(gameService::createGame);

        User newUser = (User) userService.loadUserByUsername("username");

        Assert.assertEquals(games.get(0).getUser(), newUser);
    }

    private Game makeDummyGame() {
        List<Page> pages = IntStream.range(0, 5).boxed().map(this::makeDummyPage).collect(Collectors.toList());
        return new Game(0, pages.get(0), pages.get(4), new LinkedList<>(pages), true, null);
    }

    private Page makeDummyPage(int n) {
        return new Page("title" + n, "url" + n, "pageName" + n, "description" + n);
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
