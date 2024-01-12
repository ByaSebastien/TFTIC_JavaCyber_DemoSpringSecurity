package be.tftic.spring.demo.bll.impl;

import be.tftic.spring.demo.bll.TaskService;
import be.tftic.spring.demo.domain.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final List<Task> tasks = new ArrayList<>(
            List.of(
                    new Task(1L,"vaisselle", "faire la vaisselle", 4),
                    new Task(2L,"course", "", 2),
                    new Task(3L,"laver", "", 6),
                    new Task(4L,"faire le plein", "", 9)
            )
    );

    @Override
    public Task getOne(long id) {
        return tasks.stream()
                .filter( task -> task.getId() == id )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getWithUrgencyGreaterThan(int minUrgency) {
        if( minUrgency > 10 ||minUrgency < 0 )
            throw new IllegalArgumentException("invalid minUrgency");

        return tasks.stream()
                .filter( task -> task.getUrgency() >= minUrgency )
                .toList();
    }

    @Override
    public void addTask(Task task) {
        if( task.getUrgency() > 10 || task.getUrgency() < 0 )
            throw new RuntimeException("task has invalid urgency");

        tasks.add( task );
    }
}
