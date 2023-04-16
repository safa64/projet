package com.example.projectmanagement.resource;

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
    public ResponseEntity<Project> createProject(@RequestBody Project project, @RequestParam Long userId) {
        Project createdProject = projectService.createProject(project, userId);
        return ResponseEntity.ok(createdProject);
    }


    @PutMapping("/updateProject/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("/deleteProject/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
