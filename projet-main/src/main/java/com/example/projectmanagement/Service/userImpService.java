package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.RequestAuth;
import com.example.projectmanagement.DTO.RequestRegister;
import com.example.projectmanagement.DTO.ResponseAuth;
import com.example.projectmanagement.Domaine.Authorisation;
import com.example.projectmanagement.Domaine.Task;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.AuthRepository;
import com.example.projectmanagement.Reposirtory.TaskRepository;
import com.example.projectmanagement.Reposirtory.UserRepository;
import com.example.projectmanagement.config.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor

public class userImpService implements UserSer{
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final JwtService serviceJWT;
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private AuthRepository repositoryAu;
    @Autowired
    private TaskImplServ TaskImplServ;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;



    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("No users found");
        }
        return users;
    }
    public User addUser(User user) {
        // TODO Auto-generated method stub
        return repository.save(user);
    }

    public ResponseAuth authenticate(RequestAuth request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = serviceJWT.generateToken(user);
        return ResponseAuth.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public ResponseAuth registerUser(RequestRegister request) {
        String roleName = request.getRoleName();
        // Vérifier si le rôle existe
        Authorisation role = repositoryAu.role(roleName);

        // Vérifier si le fichier image est fourni
        byte[] profilePicture = null;
        if (request.getProfilePicture() != null) {
            try {
                profilePicture = request.getProfilePicture().getBytes();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to read profile picture file", e);
            }
        }
        // Vérifier si l'email existe déjà
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        // Créer un nouvel utilisateur avec son rôle correspondant
        User user = User.builder()
                .username(request.getUsername())
                .userLastName(request.getUserLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .profilePicture(profilePicture)
                .title(request.getTitle())
                .roles(Collections.singleton(role))
                .build();

        // Enregistrer le nouvel utilisateur
        User savedUser = repository.save(user);

        // Générer un jeton JWT pour l'utilisateur
        var jwtToken = serviceJWT.generateToken(savedUser);

        // Retourner la réponse contenant le jeton JWT
        return ResponseAuth.builder()
                .token(jwtToken)
                .build();
    }


    @Override
    public User updateUserWP(User updatedUser) {
        User user = repository.findById(updatedUser.getId()).orElseThrow(EntityNotFoundException::new);
        user.setUsername(updatedUser.getUsername());
        user.setUserLastName(updatedUser.getUserLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setTitle(updatedUser.getTitle());
        return repository.save(user);
    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }
    public class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public void uploadProfilePicture(MultipartFile file, Long userId) {
        User user = repository.findById(userId).orElse(null);
        if (user != null) {
            try {
                user.setProfilePicture(file.getBytes());
                repository.save(user);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to read file", e);
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Transactional
    public User updateUser(User updatedUser) {
        User user = repository.findById(updatedUser.getId()).orElseThrow(EntityNotFoundException::new);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setUserLastName(updatedUser.getUserLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setTitle(updatedUser.getTitle());
        user.setActivated(updatedUser.isActivated());
        user.setProfilePicture(updatedUser.getProfilePicture());
        user.setRoles(updatedUser.getRoles());
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<Task> userTasks = taskRepository.findByUser(user);
        for (Task task : userTasks) {
            task.setUser(null);
            taskRepository.save(task);
        }
        repository.delete(user);
    }
    @Transactional
    public  User getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        user.getTasks();
        Hibernate.initialize(user.getRoles());
        return user;
    }





    public List<User> getUserWSUN(String ch) {
        // TODO Auto-generated method stub
        return repository.listUsers(ch);
    }


    public List<Task> findAllTasksByUserId(Long userId) {
        return repository.findAllTasksByUserId(userId);
    }


    public List<User> findAllWithoutTasks() {
        return repository.findAllWithoutTasks();
    }
    public void addRoleToUser(String email, String roleName) {
        User user = repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

       Authorisation role = repositoryAu.role(roleName);
        user.getRoles().add(role);
        repository.save(user);
    }
}
