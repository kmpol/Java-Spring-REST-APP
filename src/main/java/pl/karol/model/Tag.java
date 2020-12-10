package pl.karol.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tagName;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
