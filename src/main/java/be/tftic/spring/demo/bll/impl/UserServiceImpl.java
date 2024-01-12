package be.tftic.spring.demo.bll.impl;

import be.tftic.spring.demo.bll.UserService;
import be.tftic.spring.demo.bll.exceptions.EntityNotFoundException;
import be.tftic.spring.demo.dal.AdminRepository;
import be.tftic.spring.demo.dal.UserRepository;
import be.tftic.spring.demo.domain.entity.Post;
import be.tftic.spring.demo.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public UserServiceImpl(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getOne(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(long id) {
        User user = getOne(id);
        userRepository.delete(user);
        return user;
    }


    // user1 {'pol', 'polo'}
    // user2 {'luc', 'luke'}

    // pol, polo, luc, luke

    @Override
    public void addAlias(long userId, String alias) {
        User toUpdate = this.getOne(userId);
        boolean aliasAlreadyUsed =  userRepository.findAll().stream()
                .flatMap( user -> user.getAlias().stream() )
                .anyMatch( existingAlias -> Objects.equals( existingAlias, alias ));

        if(  aliasAlreadyUsed )
            throw new RuntimeException("alias already used");

        toUpdate.getAlias().add(alias);
        userRepository.save( toUpdate );
    }

    @Override
    public List<Post> getLastThreePostsFromUser(String username) {
        User user = userRepository.findAll().stream()
                .filter( u -> Objects.equals( u.getUsername(), username ))
                .findFirst()
                .orElseThrow( () -> new RuntimeException("No user found with username: "+ username) );

        List<Post> threeLastPosts = user.getPosts().stream()
                .sorted( (post1, post2) -> post2.getCreatedAt().compareTo( post1.getCreatedAt() ) )
                .limit(3)
                .toList();

        return threeLastPosts;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("change this")); // TODO check exception ctor
    }

    @Override
    public boolean isUserAdmin(String username) {
        return adminRepository.existsByUser(username);
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
