package be.tftic.spring.demo.api.model.form;

import be.tftic.spring.demo.api.validation.constraint.Password;
import be.tftic.spring.demo.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserUpdateForm {


    @Password// (minSize = 8)
    private String password;
    @Size(max = 3)
    // le @NotBlank dans les chevron permet la validation des éléments de la liste
    private List<@NotBlank String> alias;

    public User mapToEntity(){
        User user = new User();
        user.setPassword( this.password );
        user.setAlias( this.alias );
        return user;
    }

}
