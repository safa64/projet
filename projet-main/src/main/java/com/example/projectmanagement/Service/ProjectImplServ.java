package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ProjectRequest;
import com.example.projectmanagement.Domaine.Project;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.ProjectRepository;
import com.example.projectmanagement.Reposirtory.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjectImplServ implements ProjectServ {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public Project createProject(ProjectRequest projectRequest) {
        String email = projectRequest.getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        if (!user.hasProjectManagerRole()) {
            throw new AccessDeniedException("User does not have project manager role");
        }

        Project project = new Project();
        project.setProjectName(projectRequest.getProjectName());
        project.setDescriptionP(projectRequest.getDescriptionP());
        project.setObjectiveP(projectRequest.getObjectiveP());
        project.setDurationP(projectRequest.getDurationP());
        project.setDeadlineP(projectRequest.getDeadlineP());
        project.setProjectManager(user);

        return projectRepository.save(project);

    }



    public Project updateProject(Long projectId, Project updatedProject) {
            Project project = getProjectById(projectId);
            project.setProjectName(updatedProject.getProjectName());
            return projectRepository.save(project);
        }

        public void deleteProject(Long id) {
            projectRepository.deleteById(id);
        }
    }


