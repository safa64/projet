package com.example.projectmanagement.Service;


import com.example.projectmanagement.DTO.RequestAuth;
import com.example.projectmanagement.DTO.RequestRegister;
import com.example.projectmanagement.DTO.ResponseAuth;
import com.example.projectmanagement.Domaine.Task;
import com.example.projectmanagement.Domaine.User;

import java.util.List;

public interface UserSer {
    public List<User> getAllUsers();
    public ResponseAuth authenticate(RequestAuth request);
    public void createUserAndTask(User user, Task task);
    public User addUser(User user);
    public User updateUser(User updatedUser);
    public void deleteUser(Long id);
    public List<User> getUserWSUN(String ch);
    public List<User> findAllWithoutTasks()
;
    public ResponseAuth registerUser(RequestRegister request) ;
    public User getUserById(Long id);
    public void addRoleToUser(String userId, String roleName);}
