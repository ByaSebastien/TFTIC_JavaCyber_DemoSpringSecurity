package be.tftic.spring.demo.dal;

import be.tftic.spring.demo.dal.custom.CustomCommentRepository;
import be.tftic.spring.demo.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Long>, CustomCommentRepository {

    // SELECT c
    // FROM Comment c
    // WHERE
    //      (c.aboutPost.id BETWEEN :minPostId AND :maxPostID)  AND
    //      c.comment.id = :commentId
//    List<Comment> findByAboutPost_IdBetweenAndContent_id(Long minPostId, Long maxPostId, Long commentId);
//
//    @Query("""
//        SELECT c
//        FROM Comment c
//        WHERE c.aboutPost.id = :postId
//    """)
//    List<Comment> findByPostId(@Param("postId") Long id);
//
//    @Query(
//            value = """
//                SELECT *
//                FROM comment
//                WHERE comment_post_id = :id
//            """,
//             nativeQuery = true
//    )
//    List<Comment> findByPostIdNative(long id);

    @Query("""
        SELECT COUNT(c.id)
        FROM Comment c
        WHERE c.aboutPost.id = :postId
    """)
    long countByPostId(Long postId);
//    long countByAboutPost_Id(Long postId); // pareil qu'au dessus

}
