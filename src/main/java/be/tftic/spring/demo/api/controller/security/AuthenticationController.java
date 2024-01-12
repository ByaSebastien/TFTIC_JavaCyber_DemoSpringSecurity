package be.tftic.spring.demo.api.controller.security;

import be.tftic.spring.demo.api.model.CredentialsForm;
import be.tftic.spring.demo.api.model.dto.UserDTO;
import be.tftic.spring.demo.api.model.dto.UserTokenDTO;
import be.tftic.spring.demo.api.model.form.UserCreateForm;
import be.tftic.spring.demo.api.utils.JwtUtils;
import be.tftic.spring.demo.bll.UserService;
import be.tftic.spring.demo.bll.security.AuthenticationService;
import be.tftic.spring.demo.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserTokenDTO> register(@RequestBody UserCreateForm form){

        User user = authenticationService.register(form.mapToEntity());
        String token = jwtUtils.generateToken(user);
        UserDTO dto = UserDTO.fromEntity(user);
        return ResponseEntity.ok(new UserTokenDTO(dto,token));
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody CredentialsForm form){

        User user = authenticationService.register(form.mapToEntity());
        String token = jwtUtils.generateToken(user);
        UserDTO dto = UserDTO.fromEntity(user);
        return ResponseEntity.ok(new UserTokenDTO(dto,token));
    }
}
