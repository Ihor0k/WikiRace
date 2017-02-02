package ua.ihor0k.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.ihor0k.util.StringListConverter;

import javax.persistence.*;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_page", nullable = false)
    private Page startPage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_page", nullable = false)
    private Page endPage;

    @Column(name = "pages_list")
    @Convert(converter = StringListConverter.class)
    private LinkedList<Page> pages = new LinkedList<>();

    @Transient
    private boolean isFinished;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

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