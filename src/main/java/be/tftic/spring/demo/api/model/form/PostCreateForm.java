package be.tftic.spring.demo.api.model.form;

import be.tftic.spring.demo.domain.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostCreateForm {

    @NotBlank
    @Size(min = 5, max = 20)
    private String title;
    @NotBlank
    @Size(min = 1, max = 200)
    private String content;
    @NotNull
    private Long userId;
    @NotNull
    private Long topicId;

    public Post mapToEntity(){
        Post post = new Post();
        post.setTitle( this.title );
        post.setContent( this.content );
        return post;
    }

}
