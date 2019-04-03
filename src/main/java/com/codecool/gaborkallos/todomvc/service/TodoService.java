package com.codecool.gaborkallos.todomvc.service;


import com.codecool.gaborkallos.todomvc.modell.Status;
import com.codecool.gaborkallos.todomvc.modell.Todo;
import com.codecool.gaborkallos.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public boolean addTodo(String title) {
        Todo newTodo = new Todo(title, Status.ACTIVE);
        todoRepository.save(newTodo);
        List<Todo> allTodo = todoRepository.findAll();
        for (Todo todo : allTodo) {
            if (todo.equals(newTodo)) {
                return true;
            }
        }
        return false;
    }

    public List<Todo> listTodos(String status) {
        List<Todo> allTodo = todoRepository.findAll();
        for (Todo todo: allTodo){
            todo.setCompletedByStatus();
        }
        if (status.equals("active")) {
            List<Todo> activeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.ACTIVE)) {
                    activeTodo.add(todo);
                }
            }
            return activeTodo;
        }
        if (status.equals("complete")) {
            List<Todo> completeTodo = new ArrayList<>();
            for (Todo todo : allTodo) {
                if (todo.getStatus().equals(Status.COMPLETE)) {
                    completeTodo.add(todo);
                }
            }
            return completeTodo;
        }
        return allTodo;
    }

    public boolean deleteTodo(Long id) {
        List<Todo> allTodo = todoRepository.findAll();
        for (Todo todo : allTodo) {
            if (todo.getId().equals(id)) {
                todoRepository.delete(todo);
                return true;
            }
        }
        return false;

    }

    public boolean updateTudod(Long id, String title) {
        List<Todo> allTodo = todoRepository.findAll();
        for (Todo todo : allTodo) {
            if (todo.getId().equals(id)) {
                todo.setTitle(title);
                todoRepository.save(todo);
                return true;
            }
        }
        return false;
    }

    public boolean updateToggleTodo(Long id, boolean isComplete) {
        Optional<Todo> todoById = todoRepository.findById(id);
        if (isComplete) {
            todoById.get().setStatus(Status.COMPLETE);
            todoRepository.save(todoById.get());
        } else {
            todoById.get().setStatus(Status.ACTIVE);
            todoRepository.save(todoById.get());
        }
        todoRepository.save(todoById.get());
        return true;
    }

    public boolean toggleAll(boolean status) {
        List<Todo> allTodo = todoRepository.findAll();
        if (status) {
            for (Todo todo : allTodo) {
                todo.setStatus(Status.COMPLETE);
                todoRepository.save(todo);
            }
        } else {
            for (Todo todo : allTodo) {
                todo.setStatus(Status.ACTIVE);
                todoRepository.save(todo);
            }
        }
        return true;
    }

    public boolean deleteCompleted() {
        List<Todo> allTodo = todoRepository.findAll();
        for (Todo todo : allTodo){
            if (todo.getStatus().equals(Status.COMPLETE)){
                todoRepository.delete(todo);
            }
        }
        return true;
    }
}
