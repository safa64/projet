package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.DTO.ProjectAndActivitiesDto;
import com.example.projectmanagement.DTO.ProjectDto;
import com.example.projectmanagement.Domaine.Project;

import java.util.List;

public interface ProjectServ {
    public List<Project> getAllProjects();
    public Project getProjectById(Long id);
    public void addProjectWithActivities(ProjectAndActivitiesDto projectAndActivitiesDto);
    public Project updateProject(Long projectId, Project updatedProject);
    public void deleteProject(Long id);
    public Project createProject(Project project);
}
