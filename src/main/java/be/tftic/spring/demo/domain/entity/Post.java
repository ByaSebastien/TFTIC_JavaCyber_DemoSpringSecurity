package be.tftic.spring.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(name= "post_title", nullable = false)
    private String title;
    @Column(name = "post_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_created_by", nullable = false, updatable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "post_topic_id", nullable = false, updatable = false)
    private Topic topic;

    @Column(name = "post_created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "post_lst_update_at", insertable = false)
    private LocalDateTime lastUpdateAt;

}
