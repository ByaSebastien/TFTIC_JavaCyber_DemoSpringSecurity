package be.tftic.spring.demo.bll.security.impl;

import be.tftic.spring.demo.bll.security.AuthenticationService;
import be.tftic.spring.demo.dal.UserRepository;
import be.tftic.spring.demo.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {

        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername()).orElseThrow();
        if(!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return existingUser;
    }
}
