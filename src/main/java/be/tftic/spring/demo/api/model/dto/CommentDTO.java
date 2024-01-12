package be.tftic.spring.demo.api.model.dto;

import be.tftic.spring.demo.domain.entity.Comment;

import java.util.List;

public record CommentDTO(
        long id,
        UserDTO writtenBy,
        String content,
        List<CommentDTO> comments
) {

    public static CommentDTO fromEntity(Comment entity){
        if( entity == null )
            return null;

        return new CommentDTO(
                entity.getId(),
                UserDTO.fromEntity(entity.getWrittenBy() ),
                entity.getContent(),
                entity.getComments().stream()
                        .map(CommentDTO::fromEntity)
                        .toList()
        );
    }

}
