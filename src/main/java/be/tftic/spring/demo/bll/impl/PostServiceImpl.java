package be.tftic.spring.demo.bll.impl;

import be.tftic.spring.demo.bll.PostService;
import be.tftic.spring.demo.bll.exceptions.EntityNotFoundException;
import be.tftic.spring.demo.dal.PostRepository;
import be.tftic.spring.demo.domain.entity.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getOne(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Post.class));
    }

    @Override
    public Post create(Post post) {
        post.setId(null);
        post.setCreatedAt( LocalDateTime.now() );
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        post.setLastUpdateAt( LocalDateTime.now() );
        return postRepository.save(post);
    }

    @Override
    public Post delete(long id) {
        Post toDelete = this.getOne(id);
        postRepository.delete(toDelete);
        return toDelete;
    }

    @Override
    public List<Post> getByCreator(String username) {
        return postRepository.findByCreatorName(username);
    }

    @Override
    public List<Post> getByCreatorForLastMonth(String username) {
        LocalDateTime afterDate = LocalDateTime.now().minusMonths(1);
        return postRepository.findByUserAfterDate(username, afterDate);
    }
}
