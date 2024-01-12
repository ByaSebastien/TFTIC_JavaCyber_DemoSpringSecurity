package be.tftic.spring.demo.api.model.dto;


import be.tftic.spring.demo.domain.entity.Post;

public record PostDTO(
        Long id,
        String content,
        String title,
        UserDTO user
) {

    public static PostDTO fromEntity(Post entity){
        if( entity == null )
            return null;

        return new PostDTO(
                entity.getId(),
                entity.getContent(),
                entity.getTitle(),
                UserDTO.fromEntity( entity.getCreatedBy() )
        );
    }

}
