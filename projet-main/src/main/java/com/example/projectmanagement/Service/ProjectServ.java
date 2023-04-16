package com.example.projectmanagement.Service;

import com.example.projectmanagement.Domaine.Project;

import java.util.List;

public interface ProjectServ {
    public List<Project> getAllProjects();
    public Project getProjectById(Long id);
    public Project updateProject(Long projectId, Project updatedProject);
    public void deleteProject(Long id);
    public Project createProject(Project project, Long userId);
}
