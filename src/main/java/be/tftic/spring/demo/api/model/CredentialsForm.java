package be.tftic.spring.demo.api.model;

import be.tftic.spring.demo.domain.entity.User;
import lombok.Data;

@Data
public class CredentialsForm {
    private String username;
    private String password;

    public User mapToEntity(){
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}
