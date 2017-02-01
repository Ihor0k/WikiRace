package ua.ihor0k.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pages")
public class Page {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "page_name", nullable = false)
    private String pageName;

    @Transient
    private String html;

    @Column(name = "description", nullable = false)
    private String description;

    public Page(String title) {
        this.title = title;
    }

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
        if (!title.equals(page.title)) return false;
        return url != null ? url.equals(page.url) : page.url == null;
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
