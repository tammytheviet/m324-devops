package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.TodoRequest;

import jakarta.validation.Valid;
@CrossOrigin
@RequestMapping(path = "/task")
@RestController
public class TaskController {


    @Autowired
    private TaskRepository taskRepository;
    
    @CrossOrigin
    @GetMapping
    public ResponseEntity<Iterable<Task>> getAllTasks() {

        return ResponseEntity.ok(taskRepository.findAll()); // actual task list (internally converted to a JSON stream)
    }

    @CrossOrigin
    @PostMapping// Map ONLY Post Requests
    public ResponseEntity<String> addTask(@Valid @RequestBody TodoRequest taskRequest) {
            System.out.println("API EP '/add': '" + taskRequest.getTaskdescription() + "'");
            taskRepository.save(new Task(taskRequest.getTaskdescription()));
            return ResponseEntity.ok("redirect/");
    }

    /*
    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> delTask(@RequestBody TodoRequest taskRequest){
        System.out.println("API EP '/delete': '" + taskRequest.getTaskdescription() + "'");
        taskRepository.deleteByTaskdescription(taskRequest.getTaskdescription());
        return ResponseEntity.ok("redirect/");
    } 
    */
}
