package be.tftic.spring.demo.bll.impl;

import be.tftic.spring.demo.bll.CommentService;
import be.tftic.spring.demo.dal.CommentRepository;
import be.tftic.spring.demo.domain.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getForPost(Long id) {
        return commentRepository.findByPostIdCustom(id);
    }

    @Override
    public long countCommentForPost(Long postId) {
        return commentRepository.countByPostId(postId);
    }
}
