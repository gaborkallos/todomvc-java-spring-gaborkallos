package com.codecool.gaborkallos.todomvc.controller;


import com.codecool.gaborkallos.todomvc.modell.Todo;
import com.codecool.gaborkallos.todomvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TodoController {

    private static final String SUCCESS = "{\"success\":true}";
    private static final String FAILD = "{\"failed\":false}";

    private TodoService todoService;

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo-title") String title) {
        if (todoService.addTodo(title)) {
            return SUCCESS;
        }
        return FAILD;
    }

    @PostMapping("/list")
    public List<Todo> list(@RequestParam("status") String status) {
        List<Todo> todoList = todoService.listTodos(status);
        return todoList;
    }

    @DeleteMapping("/todos/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        if (todoService.deleteTodo(id)) {
            return SUCCESS;
        }
        return FAILD;
    }

    @PutMapping("/todos/{id}")
    public String updateTodo(@PathVariable("id") Long id, @RequestParam("todo-title") String title) {
        if (todoService.updateTudod(id, title)) {
            return SUCCESS;
        }
        return FAILD;
    }

    @PutMapping("/todos/{id}/toggle_status")
    public String togleTodo(@PathVariable("id") Long id, @RequestParam("status") boolean status) {
        if (todoService.updateToggleTodo(id, status)) {
            return SUCCESS;
        }
        return FAILD;
    }

    @PutMapping("/todos//toggle_all")
    public String toggleAllTodo(@RequestParam("toggle-all") boolean status) {
        if (todoService.toggleAll(status)) {
            return SUCCESS;
        }
        return FAILD;
    }

    @DeleteMapping("/todos/completed")
    public String deleteAllCompleted(){
        if (todoService.deleteCompleted()){
            return SUCCESS;
        }
        return FAILD;
    }
}
