package be.tftic.spring.demo.api.model.dto;

import be.tftic.spring.demo.bonus.annotation.Annotated;
import be.tftic.spring.demo.domain.entity.User;

import java.util.List;

// @Annotated uniquement présente pour la petite démo sur les annotation
@Annotated("la valeur")
public record UserDTO(
        long id,
        String username,
        List<String> alias
) {

    public static UserDTO fromEntity(User user){
        if( user == null )
            return null;

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getAlias()
        );
    }

}
