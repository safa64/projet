package com.example.projectmanagement.resource;

import com.example.projectmanagement.DTO.ProjectDto;
import com.example.projectmanagement.DTO.ProjectRequest;
import com.example.projectmanagement.Domaine.Project;
import com.example.projectmanagement.Service.ProjectImplServ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ProjectController {
    @Autowired
    private ProjectImplServ projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/getProjectById/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/createProject")
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }



    @PutMapping("/updateProject/{id}")
    public Project updateProject(@RequestBody  ProjectRequest projectRequest) {
        return projectService.updateProject(projectRequest);
    }

    @DeleteMapping("/deleteProject/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
