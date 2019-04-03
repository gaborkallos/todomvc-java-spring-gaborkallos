package com.codecool.gaborkallos.todomvc;

import com.codecool.gaborkallos.todomvc.modell.Status;
import com.codecool.gaborkallos.todomvc.modell.Todo;
import com.codecool.gaborkallos.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoMVCApplication {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoMVCApplication.class, args);
    }


    @Bean
    public CommandLineRunner init() {
        return args -> {
            Todo first = Todo.builder()
                    .status(Status.ACTIVE)
                    .title("First Todo")
                    .build();

            todoRepository.save(first);

            Todo second = Todo.builder()
                    .status(Status.ACTIVE)
                    .title("Second Todo")
                    .build();

            todoRepository.save(second);

            Todo third = Todo.builder()
                    .status(Status.COMPLETE)
                    .title("Third Todo")
                    .build();

            todoRepository.save(third);
        };
    }
}
