package be.tftic.spring.demo.bll;

import be.tftic.spring.demo.domain.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();
    Post getOne(long id);

    Post create(Post post);

    Post update(Post post);

    Post delete(long id);

    List<Post> getByCreator(String username);

    List<Post> getByCreatorForLastMonth(String username);


}
