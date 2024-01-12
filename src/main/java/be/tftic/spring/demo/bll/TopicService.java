package be.tftic.spring.demo.bll;

import be.tftic.spring.demo.domain.entity.Topic;
import be.tftic.spring.demo.domain.entity.TopicCategory;

import java.util.List;

public interface TopicService {

    Topic getOne(long id);

    List<Topic> getByCategory(TopicCategory category);

}
