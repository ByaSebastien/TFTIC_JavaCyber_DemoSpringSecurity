package be.tftic.spring.demo.api.controller;

import be.tftic.spring.demo.api.model.dto.UserDTO;
import be.tftic.spring.demo.api.model.form.UserCreateForm;
import be.tftic.spring.demo.api.model.form.UserUpdateForm;
import be.tftic.spring.demo.bll.UserService;
import be.tftic.spring.demo.domain.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        List<User> users = userService.getAll();
        List<UserDTO> dtos = users.stream()
                .map( UserDTO::fromEntity)
                .toList();
        return ResponseEntity.ok( dtos );
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable long id){
        User user = userService.getOne(id);
        UserDTO dto = UserDTO.fromEntity(user);
        return ResponseEntity.ok( dto );
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateForm form){
        User user = userService.create( form.mapToEntity() );
        UserDTO dto = UserDTO.fromEntity(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable long id, @RequestBody @Valid UserUpdateForm form ){
        User toUpdate = userService.getOne(id);
        toUpdate.setPassword( form.getPassword() );
        toUpdate.setAlias( form.getAlias() );
        User user = userService.update(toUpdate);
        return ResponseEntity.ok(
                UserDTO.fromEntity(user)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable long id){
        User user = userService.delete( id );
        UserDTO dto = UserDTO.fromEntity( user );
        return ResponseEntity.ok( dto );
    }

    // PATCH - http://localhost:8080/user/1
    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    public ResponseEntity<?> addAlias(
            Authentication authentication,
            @RequestParam String newAlias
    ){
        User user = (User) authentication.getPrincipal();
        String token = (String) authentication.getCredentials();
        userService.addAlias(user.getId(), newAlias);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping(params = "username")
    public ResponseEntity<UserDTO> getByUsername(@RequestParam String username){
        return ResponseEntity.ok(
                UserDTO.fromEntity( userService.getByUsername(username) )
        );
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isUserAdmin(@RequestParam String username){
        return ResponseEntity.ok(
                userService.isUserAdmin(username)
        );
    }

    @DeleteMapping(params = "username")
    public ResponseEntity<Void> deleteByUsername(@RequestParam String username){
        userService.delete(username);
        return ResponseEntity.ok().build();
    }

}
