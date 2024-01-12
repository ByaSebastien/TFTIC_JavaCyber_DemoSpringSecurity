package be.tftic.spring.demo.bll;

import be.tftic.spring.demo.domain.entity.Post;
import be.tftic.spring.demo.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();
    User getOne(long id);

    User create(User user);

    User update(User user);

    User delete(long id);
    void delete(String username);

    void addAlias(long userId, String alias);

    List<Post> getLastThreePostsFromUser(String username);


    User getByUsername(String username);

    boolean isUserAdmin(String username);
}
