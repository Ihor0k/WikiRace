package ua.ihor0k.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Page startPage;
    private Page endPage;

    private LinkedList<Page> pages = new LinkedList<>();

    private boolean isFinished;

    public Game(Page startPage, Page endPage) {
        this.startPage = startPage;
        this.endPage = endPage;
    }

    public void addPage(Page page) {
        if (!page.equals(pages.peekLast())) {
            pages.add(page);
            if (page.equals(endPage))
                isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return startPage.equals(game.startPage) && endPage.equals(game.endPage);
    }

    @Override
    public int hashCode() {
        int result = startPage.hashCode();
        result = 31 * result + endPage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return Integer.toHexString(hashCode()).toUpperCase();
    }
}