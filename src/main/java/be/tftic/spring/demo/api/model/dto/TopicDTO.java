package be.tftic.spring.demo.api.model.dto;

import be.tftic.spring.demo.domain.entity.Topic;
import be.tftic.spring.demo.domain.entity.TopicCategory;

public record TopicDTO(
        Long id,
        String title,
        String description,
        TopicCategory category,
        UserDTO createdBy
) {

    public static TopicDTO fromEntity(Topic entity){
        if( entity == null )
            return null;

        return new TopicDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCategory(),
                UserDTO.fromEntity( entity.getCreatedBy() )
        );

    }

}
