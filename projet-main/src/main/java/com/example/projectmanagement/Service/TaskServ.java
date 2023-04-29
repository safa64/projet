package com.example.projectmanagement.Service;


import com.example.projectmanagement.DTO.TaskDto;
import com.example.projectmanagement.Domaine.Task;

import java.util.List;

public interface TaskServ {

    public Task getTaskById(Long id);
    public Task createTask(TaskDto taskDto);
    public Task updateTask(TaskDto taskDto, Long id);
    public void deleteTask(Long id);
    public List<Task> getTasksByUserId(Long userId);
    public List<Task> getAllTasks();
    public List<Task> getAllTasksOfUser(String username);

}
