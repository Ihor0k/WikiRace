package ua.ihor0k.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ua.ihor0k.api.ApiWorker;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.Page;

@Slf4j
@Service
@SessionScope
public class GameManager {
    private ApiWorker apiWorker;
    private Game game;

    public boolean isFinished() {
        boolean result = game.isFinished();
        if (result)
            log.info("Game: {}. Clicks: {}.", game, game.getPages().size() - 1);
        return result;
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
        log.info("Game: {}.", game);
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
    public void setApiWorker(ApiWorker apiWorker) {
        this.apiWorker = apiWorker;
    }
}