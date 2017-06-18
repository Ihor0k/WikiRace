package ua.ihor0k.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.ihor0k.manager.GameManager;

@Controller
public class GameController {
    private GameManager gameManager;

    @RequestMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("game", gameManager.getGame());
        return "index";
    }

    @RequestMapping(value = "/game")
    public String game(Model model, @RequestParam(value = "action", required = false) String action) {
        model.addAttribute("game", gameManager.getGame());
        if (action != null)
            if (action.equals("stop")) {
                gameManager.stopGame();
                return "redirect:/";
            } else return "redirect:/game";
        return "game";
    }

    @RequestMapping(value = "/game/{id}")
    public String gameById(Model model, @PathVariable("id") int id) {
        model.addAttribute("game", gameManager.getGameById(id));
        return "game";
    }

    @RequestMapping(value = "/wiki/{pageName:.+}")
    public String wiki(@PathVariable("pageName") String pageName, Model model) {
        gameManager.addPage(pageName);
        if (gameManager.isFinished()) {
            model.addAttribute("game", gameManager.getGame());
            gameManager.stopGame();
            return "results";
        }
        model.addAttribute("page", gameManager.getLastPage());
        return "wiki";
    }

    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
