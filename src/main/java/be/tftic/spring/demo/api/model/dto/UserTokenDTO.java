package be.tftic.spring.demo.api.model.dto;

import lombok.Data;

@Data
public class UserTokenDTO {

    private UserDTO user;
    private String token;

    public UserTokenDTO() {
    }

    public UserTokenDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }
}
