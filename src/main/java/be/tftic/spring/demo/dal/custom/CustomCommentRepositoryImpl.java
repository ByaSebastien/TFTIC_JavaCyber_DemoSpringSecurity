package be.tftic.spring.demo.dal.custom;

import be.tftic.spring.demo.domain.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

// Il faut que le nom soit le meme que l'interface custom mais avec 'Impl' à la fin
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Comment> findByPostIdCustom(Long id) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.aboutPost.id = :postId", Comment.class);
        query.setParameter("postId", id);
        return query.getResultList();
    }
}
