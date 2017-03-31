package news;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "news")
public class News implements Serializable {
    private static final long serialVersionUID = 35146646513L;
    public static final int NEWS_ON_PAGE = 10;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name = "datetime")
    @DateTimeFormat(style = "M")
    private Date datetime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(datetime);
        sb.append(" || категория: ").append(category);
        sb.append(" || ").append(title);
        sb.append(" || ").append(content.length() > 50 ? content.substring(0, 50) : content);
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public News(){}
}
