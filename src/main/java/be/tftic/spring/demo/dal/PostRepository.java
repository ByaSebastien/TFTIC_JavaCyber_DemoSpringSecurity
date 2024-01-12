package be.tftic.spring.demo.dal;

import be.tftic.spring.demo.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("""
        SELECT p
        FROM Post p
        WHERE p.createdBy.username = :username
    """)
    List<Post> findByCreatorName(String username);
//    List<Post> findByCreatedBy_Username(String username); // meme chose qu'au dessus



    @Query("""
        SELECT p
        FROM Post p
        WHERE p.createdBy.username = :username AND p.createdAt > :createdAfter
    """)
    List<Post> findByUserAfterDate(String username, LocalDateTime createdAfter);
//    List<Post> findByCreatedBy_UsernameAndCreatedAtAfter(String username, LocalDateTime createdAfter); // pareil qu'au dessus

}
