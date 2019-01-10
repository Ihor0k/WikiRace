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
import java.util.stream.IntStream;

@Component
public class GamePool {
    private final static int POOL_SIZE = 10;

    private ApiWorker apiWorker;

    private LinkedBlockingQueue<Game> pool;

    public GamePool() {
        pool = new LinkedBlockingQueue<>(POOL_SIZE);
    }

    @PostConstruct
    private void init() {
        IntStream.range(0, POOL_SIZE).parallel().forEach(__ -> addGame());
    }

    @SneakyThrows(InterruptedException.class)
    public Game getGame(){
        return pool.take();
    }

    @Async
    public void addGame() {
        Page startPage = apiWorker.getRandomPage();
        Page endPage = apiWorker.getRandomPage();
        pool.offer(new Game(startPage, endPage));
    }

    @Autowired
    public void setApiWorker(ApiWorker apiWorker) {
        this.apiWorker = apiWorker;
    }
}
