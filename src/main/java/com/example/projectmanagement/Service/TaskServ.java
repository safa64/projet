package com.example.projectmanagement.Service;


import com.example.projectmanagement.Domaine.Task;

import java.util.List;

public interface TaskServ {

    public Task getTaskById(Long id);
    public Task createTask(Task task);
    public Task updateTask(Task task, Long id);
    public void deleteTask(Long id);
    public List<Task> getTasksByUserId(Long userId);
    public List<Task> getAllTasks();
    public List<Task> getAllTasksOfUser(String username);

}
