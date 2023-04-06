package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.DTO.ProjectAndActivitiesDto;
import com.example.projectmanagement.DTO.ProjectDto;
import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Domaine.Project;
import com.example.projectmanagement.Domaine.Team;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProjectImplServ implements ProjectServ{
    @Autowired
    private EntityManager entityManager;
   @Autowired
    private  ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void addProjectWithActivities(ProjectAndActivitiesDto projectAndActivitiesDto) {
        ProjectDto projectDto = projectAndActivitiesDto.getProjectDto();
        List<ActivityDto> activityDtos = projectAndActivitiesDto.getActivityDtos();
        //...
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
