package com.example.projectmanagement.resource;

import com.example.projectmanagement.DTO.CreateUserAndTaskRequest;
import com.example.projectmanagement.DTO.RequestAuth;
import com.example.projectmanagement.DTO.RequestRegister;
import com.example.projectmanagement.DTO.ResponseAuth;
import com.example.projectmanagement.Domaine.Task;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.TaskRepository;
import com.example.projectmanagement.Service.userImpService;
import com.example.projectmanagement.config.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class Usercontroller {


    @Autowired
    private userImpService service;
    private final JwtService serviceJWT;

  //  @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<ResponseAuth> registerUser(@RequestBody RequestRegister request) {
        ResponseAuth response = service.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<ResponseAuth> authenticate(

            @RequestBody RequestAuth request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping(value = "/createUserAndTask")
    public ResponseEntity<User> createUserAndTask(@RequestBody CreateUserAndTaskRequest request) {
        User user = request.getUser();
        Task task = request.getTask();
        if (user == null || task == null) {
            return ResponseEntity.badRequest().build();
        }
        service.createUserAndTask(user, task);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/upload-profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        service.uploadProfilePicture(file, userId);
        return new ResponseEntity<>("Profile picture uploaded successfully", HttpStatus.OK);
    }


    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }




    @GetMapping(value = "/getAllUserBSUN/{str}")
    public List<User> getAllUserBSUN(@PathVariable("str") String str)

    {
        return service.getUserWSUN(str);
    }
    @GetMapping("/getuserId")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }



    @GetMapping(value = "/getAllUserBID/{userId}")
    public List<Task> getTasksForUser(@PathVariable Long userId) {
        return service.findAllTasksByUserId(userId);
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody  User user)
    {
        return service.addUser(user);
    }

    @GetMapping("/withoutTasks")
    public ResponseEntity<List<User>> findAllUsersWithoutTasks() {
        List<User> users = service.findAllWithoutTasks();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody Map<String, String> requestParams) {
        String username = requestParams.get("username");
        String roleName = requestParams.get("roleName");
        service.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }


}
