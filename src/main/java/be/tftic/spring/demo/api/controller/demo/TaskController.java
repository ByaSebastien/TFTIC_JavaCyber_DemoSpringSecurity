package be.tftic.spring.demo.api.controller.demo;

import be.tftic.spring.demo.bll.TaskService;
import be.tftic.spring.demo.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET - http://localhost:8080/task/_
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable long id){
        try{
            return ResponseEntity.ok( service.getOne(id) );
        }
        catch (RuntimeException ex){
            // TODO faire mieux
            return ResponseEntity.notFound()
                    .build();
        }
    }

    // GET - http://localhost:8080/task?minUrgency=_
    @GetMapping
    public ResponseEntity<List<Task>> getWithMinUrgency(@RequestParam int minUrgency) {
        List<Task> body = service.getWithUrgencyGreaterThan( minUrgency );
//        return ResponseEntity.ok().body(body);
        return ResponseEntity.ok(body);
    }

    // POST - http://localhost:8080/task
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Task task){
        service.addTask(task);
//        return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("mon header", "valeur1", "value2")
                .header("mon header2", "valeur1", "value2")
                .build();
    }

}
