package ua.ihor0k.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.ihor0k.manager.GameManager;
import ua.ihor0k.service.SecurityService;

@Controller
public class GameController {
    private GameManager gameManager;
    private SecurityService securityService;

    @RequestMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("game", gameManager.getGame());
        model.addAttribute("user", securityService.getLoggedInUser());
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
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
