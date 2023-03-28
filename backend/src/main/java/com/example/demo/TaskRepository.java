package com.example.demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    
public void deleteByTaskdescription(String taskdescription);

public Optional<Task> findByTaskdescription(String taskdescription);
}
