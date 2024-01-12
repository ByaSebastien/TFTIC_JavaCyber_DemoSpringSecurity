package be.tftic.spring.demo.api.controller;

import be.tftic.spring.demo.api.model.dto.CommentDTO;
import be.tftic.spring.demo.api.model.dto.PostDTO;
import be.tftic.spring.demo.api.model.form.PostCreateForm;
import be.tftic.spring.demo.api.model.form.PostUpdateForm;
import be.tftic.spring.demo.bll.CommentService;
import be.tftic.spring.demo.bll.PostService;
import be.tftic.spring.demo.bll.TopicService;
import be.tftic.spring.demo.bll.UserService;
import be.tftic.spring.demo.domain.entity.Post;
import be.tftic.spring.demo.domain.entity.Topic;
import be.tftic.spring.demo.domain.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final TopicService topicService;
    private final CommentService commentService;

    public PostController(
            PostService postService,
            UserService userService,
            TopicService topicService, CommentService commentService
    ) {
        this.postService = postService;
        this.userService = userService;
        this.topicService = topicService;
        this.commentService = commentService;
    }

    // GET - http://localhost:8080/post
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(){
        List<Post> posts = postService.getAll();
        List<PostDTO> dtos = posts.stream()
                .map( PostDTO::fromEntity)
                .toList();
        return ResponseEntity.ok( dtos );
    }

    // GET - http://localhost:8080/post/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable long id){
        Post post = postService.getOne( id );
        PostDTO dto = PostDTO.fromEntity( post );
        return ResponseEntity.ok( dto );
    }

    // POST - http://localhost:8080/post
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PostDTO> create(@RequestBody @Valid PostCreateForm form){
        Post toCreate = form.mapToEntity();
        User creator = userService.getOne( form.getUserId() );
        Topic topic = topicService.getOne( form.getTopicId() );
        toCreate.setCreatedBy( creator );
        toCreate.setTopic( topic );

        Post created = postService.create( toCreate );
        PostDTO dto = PostDTO.fromEntity( created );
        return ResponseEntity.status( HttpStatus.CREATED )
                .body( dto );
    }

    // PUT - http://localhost:8080/post/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable long id, @RequestBody @Valid PostUpdateForm form){
        Post toUpdate = postService.getOne(id);
        toUpdate.setTitle(form.getTitle());
        toUpdate.setContent(form.getContent());
        Post updated = postService.update(toUpdate);
        PostDTO dto = PostDTO.fromEntity( updated );
        return ResponseEntity.ok( dto );
    }

    // DELETE - http://localhost:8080/post/1
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> delete(@PathVariable long id){
        Post deleted = postService.delete(id);
        PostDTO dto = PostDTO.fromEntity(deleted);
        return ResponseEntity.ok(dto);
    }

    // GET - http://localhost:8080/post/last?username=*
    @GetMapping(path = "/lastThree",params = "username")
    public ResponseEntity<List<PostDTO>> lastThreeFrom(@RequestParam String username){
        List<Post> posts = userService.getLastThreePostsFromUser(username);
        List<PostDTO> dtos = posts.stream()
                .map( PostDTO::fromEntity)
                .toList();

        return ResponseEntity.ok( dtos );
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable long id){
        return ResponseEntity.ok(
                commentService.getForPost(id).stream()
                        .map( CommentDTO::fromEntity )
                        .toList()
        );
    }

    @GetMapping(params = "username")
    public ResponseEntity<List<PostDTO>> getPostByCreator(@RequestParam(required = false) String username){
        return ResponseEntity.ok(
                postService.getByCreator(username).stream()
                        .map(PostDTO::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/{id}/comment/count")
    public ResponseEntity<Long> getCommentCount(@PathVariable long id){
        return ResponseEntity.ok(
                commentService.countCommentForPost(id)
        );
    }

    @GetMapping(path = "/lastMonth", params = "username")
    public ResponseEntity<List<PostDTO>> getByCreatorForLastMonth(@RequestParam String username){
        return ResponseEntity.ok(
                postService.getByCreatorForLastMonth(username).stream()
                        .map(PostDTO::fromEntity)
                        .toList()
        );
    }

}
