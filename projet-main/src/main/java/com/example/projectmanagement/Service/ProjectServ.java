package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ProjectDto;
import com.example.projectmanagement.DTO.ProjectRequest;
import com.example.projectmanagement.Domaine.Project;

import java.util.List;

public interface ProjectServ {
    public List<Project> getAllProjects();
    public Project getProjectById(Long id);
    public Project updateProject( ProjectRequest projectRequest);
    public void deleteProject(Long id);
    public Project createProject(ProjectRequest projectRequest);
}
