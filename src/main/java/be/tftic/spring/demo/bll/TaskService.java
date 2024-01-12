package be.tftic.spring.demo.bll;

import be.tftic.spring.demo.domain.Task;

import java.util.List;

public interface TaskService {

    Task getOne(long id);
    List<Task> getWithUrgencyGreaterThan(int minUrgency);
    void addTask(Task task);

}
