package be.tftic.spring.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter @Setter
@ToString
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private long id;

    @Column(name = "topic_title", nullable = false)
    private String title;

    @Column(name = "topic_desc")
    public String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_category", updatable = false, nullable = false)
    public TopicCategory category;

    @Column(name = "topic_views", nullable = false)
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "topic_created_by")
    private User createdBy;

    @ManyToMany(mappedBy = "followedTopics")
    private List<User> followers;

    @OneToMany(mappedBy = "topic")
    private List<Post> posts;
}
