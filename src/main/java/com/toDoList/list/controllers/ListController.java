package com.toDoList.list.controllers;

import com.toDoList.list.data.TaskCreator;
import com.toDoList.list.data.TaskDTO;
import com.toDoList.list.data.TaskEntity;
import com.toDoList.list.services.ListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class ListController {

    private final ListService listService;

    @DeleteMapping("/todoList/{id}")
    public void deleteTask(@PathVariable Long id) {
        listService.deleteTask(id);
    }

    @PutMapping("/todoList/{id}")
    public void updateTask(@PathVariable Long id,@RequestBody TaskDTO updatedTask) {
        listService.updateTask(id,updatedTask);
    }

    @PostMapping("/todoList")
    public TaskEntity addTask(@RequestBody TaskCreator newToDoListElement) {
        return listService.addTask(newToDoListElement);
    }

    @GetMapping("/todoList")
    public List<TaskEntity> getTasks() {
        return listService.getTasks();
    }
}
