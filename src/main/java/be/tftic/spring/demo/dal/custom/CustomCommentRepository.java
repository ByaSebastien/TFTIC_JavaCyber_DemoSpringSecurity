package be.tftic.spring.demo.dal.custom;

import be.tftic.spring.demo.domain.entity.Comment;

import java.util.List;

public interface CustomCommentRepository {

    List<Comment> findByPostIdCustom(Long id);

}
