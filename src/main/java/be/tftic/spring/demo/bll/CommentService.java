package be.tftic.spring.demo.bll;


import be.tftic.spring.demo.domain.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getForPost(Long id);

    long countCommentForPost(Long postId);

}
