package ua.ihor0k.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ua.ihor0k.api.ApiWorker;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.Page;
import ua.ihor0k.model.User;
import ua.ihor0k.service.SecurityService;
import ua.ihor0k.service.UserService;

@Slf4j
@Service
@SessionScope
public class GameManager {
    private ApiWorker apiWorker;
    private UserService userService;
    private SecurityService securityService;

    private Game game;

    public boolean isFinished() {
        return game == null || game.isFinished();
    }

    public void addPage(String pageName) {
        Page page = getPage(pageName);
        game.addPage(page);
        if (!game.isFinished() && page.getHtml() == null)
            page.setHtml(apiWorker.getBodyHtml(pageName));
        log.info("Game: {}. Page: {}.", game, page.getTitle());
    }

    private Page getPage(String pageName) {
        Page page;
        if (pageName.equals(game.getStartPage().getPageName()))
            page = game.getStartPage();
        else if (pageName.equals(game.getEndPage().getPageName()))
            page = game.getEndPage();
        else
            page = apiWorker.getPage(pageName);
        return page;
    }

    public Page getLastPage() {
        return game.getPages().peekLast();
    }

    private void newGame() {
        Page startPage = apiWorker.getRandomPage();
        Page endPage = apiWorker.getRandomPage();
        game = new Game(startPage, endPage);
        log.info("Game: {}. From: {} To: {}.", game, startPage.getTitle(), endPage.getTitle());
    }

    public void stopGame() {
        if (isFinished()) {
            log.info("Game: {} is finished. Clicks: {}.", game, game.getPages().size() - 1);
            User user = securityService.getLoggedInUser();
            if (user != null) {
                user.addGame(game);
                userService.updateUser(user);
            }
        } else
            log.info("Game: {} is stopped.", game);
        game = null;
    }

    public Game getGame() {
        if (game == null)
            newGame();
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setApiWorker(ApiWorker apiWorker) {
        this.apiWorker = apiWorker;
    }
}