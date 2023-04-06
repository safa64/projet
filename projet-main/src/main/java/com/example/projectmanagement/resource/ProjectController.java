package com.example.projectmanagement.resource;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.DTO.ProjectAndActivitiesDto;
import com.example.projectmanagement.DTO.ProjectDto;
import com.example.projectmanagement.Domaine.Project;
import com.example.projectmanagement.Service.ProjectImplServ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> addProjectWithActivities(@RequestBody ProjectAndActivitiesDto projectAndActivitiesDto) {
        projectService.addProjectWithActivities(projectAndActivitiesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/createProject1")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
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
