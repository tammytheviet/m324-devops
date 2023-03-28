package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class DemoApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testRepository() {
        assertNotNull(taskRepository);
    }

    @Test
    public void testAddEntry() {
        // given
        final String taskdescription = "test";

        //when
        Task test = new Task(taskdescription);

        taskRepository.save(test);

        //then
        Optional<Task> result = taskRepository.findByTaskdescription(taskdescription);
        assertNotNull(result);
        assertEquals(result.get().getTaskdescription(), taskdescription);
    }
}
