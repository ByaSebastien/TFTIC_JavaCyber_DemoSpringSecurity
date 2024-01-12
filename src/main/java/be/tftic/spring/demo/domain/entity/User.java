package be.tftic.spring.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Getter @Setter
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_username",
            nullable = false,
            insertable = true,
            updatable = false,
            unique = true,
//            columnDefinition = "VARCHAR(50)",
            length = 50
    )
    private String username;
    @Column( name = "user_password", nullable = false)
    private String password;

    @ElementCollection
    private List<String> alias;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<TopicCategory> favoriteCategories;

//    @Embedded
    private Address address;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private List<Topic> createdTopics;

    @ManyToMany
    @JoinTable(
            name = "followed_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    @ToString.Exclude
    private List<Topic> followedTopics;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private Admin admin;

    @OneToMany(mappedBy = "createdBy")
    private List<Post> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = admin == null ? "USER" : "ADMIN";
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
