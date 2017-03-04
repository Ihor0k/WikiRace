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
import java.util.Random;
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
        Game game = buildGame();
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

        List<Game> games = Arrays.asList(buildGame(), buildGame(), buildGame());
        games.forEach(user::addGame);

        userService.createUser(user);

        User newUser = (User) userService.loadUserByUsername("username");

        Assert.assertEquals(user, newUser);}

    @Test
    @Rollback
    @Transactional
    public void gamesWithOneUser() {
        User user = new User("username", "password");

        List<Game> games = Arrays.asList(buildGame(), buildGame(), buildGame());
        games.forEach(game -> game.setUser(user));
        games.forEach(gameService::createGame);

        User newUser = (User) userService.loadUserByUsername("username");

        Assert.assertEquals(games.get(0).getUser(), newUser);
    }

    private Game buildGame() {
        Random r = new Random();
        List<Page> pages = IntStream.range(0, 5).boxed().map((n) ->
                new Page("title" + r.nextInt(10), "url" + r.nextInt(10),
                        "pageName" + r.nextInt(10), "description" + r.nextInt(10)))
                .collect(Collectors.toList());
        return new Game(0, pages.get(0), pages.get(4), new LinkedList<Page>(pages), true, null);
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
