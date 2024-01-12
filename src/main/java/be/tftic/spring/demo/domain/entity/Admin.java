package be.tftic.spring.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long id;

    @Column(name = "admin_email", nullable = false, unique = true)
    private String email;

    @OneToOne
    private User user;
}
