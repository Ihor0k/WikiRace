package ua.ihor0k.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ihor0k.game.managers.GameManager;

@Controller
public class MainController {
    private GameManager gameManager;

    @RequestMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("game", gameManager.getGame());
        return "main";
    }

    @RequestMapping(value = "/game")
    public String game(Model model) {
        model.addAttribute("game", gameManager.getGame());
        return "game";
    }

    @RequestMapping(value = "/wiki/{pageName:.+}")
    public String wiki(@PathVariable("pageName") String pageName, Model model) {
        gameManager.addPage(pageName);
        if (gameManager.isFinished()) {
            model.addAttribute("game", gameManager.getGame());
            return "results";
        }
        model.addAttribute("page", gameManager.getLastPage());
        return "wiki";
    }

    @RequestMapping(value = "/game/stop")
    public String stopGame() {
        gameManager.stopGame();
        return "redirect:/";
    }

    @RequestMapping(value = "/game/new")
    public String newGame(Model model) {
        gameManager.stopGame();
        return game(model);
    }

    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
