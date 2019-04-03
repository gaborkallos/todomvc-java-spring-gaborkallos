package com.codecool.gaborkallos.todomvc.repository;

import com.codecool.gaborkallos.todomvc.modell.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
