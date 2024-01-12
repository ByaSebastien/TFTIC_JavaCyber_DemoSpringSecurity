package be.tftic.spring.demo.bll.security;

import be.tftic.spring.demo.domain.entity.User;

public interface AuthenticationService {

    User register(User user);
    User login(User user);
}
