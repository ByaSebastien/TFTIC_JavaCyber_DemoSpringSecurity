package be.tftic.spring.demo.dal;

import be.tftic.spring.demo.domain.entity.Topic;
import be.tftic.spring.demo.domain.entity.TopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("""
        SELECT t
        FROM Topic t
        WHERE t.category = :category
    """)
    List<Topic> findByCategory(TopicCategory category);
//    List<Topic> findByCategory(TopicCategory category); // pareil qu'au dessus



}
