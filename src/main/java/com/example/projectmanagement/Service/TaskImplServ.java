package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.DTO.TaskDto;
import com.example.projectmanagement.DTO.UserDto;
import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Domaine.Task;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.TaskRepository;
import com.example.projectmanagement.Reposirtory.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskImplServ implements TaskServ{



    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final UserRepository Repository;



    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }



    public Task updateTask(Task task, Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task updatedTask = optionalTask.get();
            updatedTask.setDescription(task.getDescription());
            updatedTask.setTitle(task.getTitle());
            return taskRepository.save(updatedTask);
        } else {
            throw new NotFoundException("Task not found with id: " + id);

        }
    }
    public class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }



    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }
    public List<Task> getAllTasksOfUser(String email) {
        User user = Repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTasks();
    }


   // public List<Task> getTasksByUserId(Long userId) {
    //    User user = new User();
    //    user.setId(userId);
    //    return taskRepository.findByUser(user);
    //}



    @Transactional()
    public List<Task> getAllTasksWithUserAndActivity() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            User user = task.getUser();
            if (user != null) {
                user.getTasks().size(); // Load tasks for user
            }
            Activity activity = task.getActivity();
            if (activity != null) {
                activity.getTasks().size(); // Load tasks for activity
            }
        }
        return tasks;
    }



}
