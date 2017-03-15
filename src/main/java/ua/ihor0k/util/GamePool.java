package ua.ihor0k.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ua.ihor0k.api.ApiWorker;
import ua.ihor0k.model.Game;
import ua.ihor0k.model.Page;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class GamePool {
    private ApiWorker apiWorker;

    private LinkedBlockingQueue<Game> pool = new LinkedBlockingQueue<>(10);

    @PostConstruct
    private void init() {
        for (int i = 0; i < 10; i++)
            addGame();
    }

    @SneakyThrows(InterruptedException.class)
    public Game getGame(){
        Game game = pool.take();
        addGame();
        return game;
    }

    @Async
    private void addGame() {
        Page startPage = apiWorker.getRandomPage();
        Page endPage = apiWorker.getRandomPage();
        pool.offer(new Game(startPage, endPage));
    }

    @Autowired
    public void setApiWorker(ApiWorker apiWorker) {
        this.apiWorker = apiWorker;
    }
}
