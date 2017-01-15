package ua.ihor0k.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private String title;
    private String url;
    private String pageName;
    private String html;
    private String description;

    public Page(String title, String url, String pageName) {
        this.title = title;
        this.url = url;
        this.pageName = pageName;
    }

    public Page(String title, String url, String pageName, String description) {
        this.title = title;
        this.url = url;
        this.pageName = pageName;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return title.equals(page.title) && url.equals(page.url);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
