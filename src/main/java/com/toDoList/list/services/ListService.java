package com.toDoList.list.services;


import com.toDoList.list.data.TaskCreator;
import com.toDoList.list.data.TaskDTO;
import com.toDoList.list.data.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ListService {
    private List<TaskEntity> taskList;

    public List<TaskEntity> getTasks() {
        return taskList != null ? taskList : new ArrayList<>();
    }

    public TaskEntity addTask(TaskCreator newTask) {
        if (taskList == null) {
            taskList = new ArrayList<>();
        }

        long newId = taskList.isEmpty() ? 1 : taskList.get(taskList.size() - 1).getId() + 1;
        TaskEntity newTaskElement = createListElementObject(newId, newTask.getContents());
        taskList.add(newTaskElement);
        return newTaskElement;
    }

    private TaskEntity createListElementObject(long id, String taskContent) {
        return TaskEntity.builder()
                .id(id)
                .contents(taskContent)
                .done(false)
                .build();
    }

    public void deleteTask(long id) {
        if (taskList == null || taskList.isEmpty()) {
            throw new RuntimeException("Element o podanym ID nie istnieje");
        }

        taskList.removeIf(task -> task.getId().equals(id));
    }

    public void updateTask(long id, TaskDTO updatedTask) {
        Optional<TaskEntity> optionalTask = taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();

        if (optionalTask.isPresent()) {
            TaskEntity existingTask = optionalTask.get();
            existingTask.setDone(updatedTask.isDone());
            existingTask.setContents(updatedTask.getContents());
        } else {
            System.out.println("Element o podanym ID nie istnieje");
        }
    }

}
