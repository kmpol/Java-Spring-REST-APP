package pl.karol.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;
    @Column(length = 128)
    @NotEmpty(message = "{pl.karol.model.Post.title.NotEmpty}")
    private String title;
    @Column(length = 1023)
    private String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "posts_tags",
            joinColumns = {@JoinColumn(name = "id_post", referencedColumnName = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag", referencedColumnName = "id")}
    )
    private List<Tag> tags;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonIgnoreProperties("post")
    private List<Comment> comments;

    public Post() {
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addTag(Tag tag){
        tags.add(tag);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags.clear();
        if(!tags.isEmpty()){
            this.tags.addAll(tags);
        }

    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments.clear();
        if(!comments.isEmpty()) {
            this.comments.addAll(comments);
        }
    }
}
