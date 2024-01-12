package be.tftic.spring.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comment")
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_user_id", nullable = false, updatable = false)
    private User writtenBy;

    @Column(name = "comment_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "comment_post_id", updatable = false)
    private Post aboutPost;

    @ManyToOne
    @JoinColumn(name = "comment_comment_id", updatable = false)
    private Comment aboutComment;

    @OneToMany(mappedBy = "aboutComment")
    private List<Comment> comments;

}
