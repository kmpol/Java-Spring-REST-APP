package pl.karol.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
    }

    public Comment(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
