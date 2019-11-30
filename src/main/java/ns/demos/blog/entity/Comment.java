package ns.demos.blog.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Comment extends AuditableEntity {

    @NotBlank(message = "Comment author name is mandatory")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "Text of comment is mandatory")
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    public Comment() {}

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }
}
