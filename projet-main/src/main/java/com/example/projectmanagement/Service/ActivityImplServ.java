package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.DTO.ProjectRequest;
import com.example.projectmanagement.Domaine.*;
import com.example.projectmanagement.Reposirtory.ActivityRepository;
import com.example.projectmanagement.Reposirtory.ProjectRepository;
import com.example.projectmanagement.Reposirtory.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityImplServ implements ActitvtyServ{
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TeamRepository teamRepository;


    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivityByProjectId(Long id) {
        return activityRepository.findByProjectId(id);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Activity createActivity(ActivityDto activityDto) {
        Project project= projectRepository.findById(activityDto.getProjectId()).orElseThrow(()
                -> new IllegalArgumentException("Invalid project id"));
        Team team = teamRepository.findById(activityDto.getTeamId()).orElseThrow(()
                -> new IllegalArgumentException("Invalid team id"));
        Activity activity = new Activity();
        activity.setActivityName(activityDto.getActivityName());
        activity.setDescriptionA(activityDto.getDescriptionA());
        activity.setObjectiveA(activityDto.getObjectiveA());
        activity.setDurationA(activityDto.getDurationA());
        activity.setDeadlineA(activityDto.getDeadlineA());
        activity.setProject(project);
        activity.setTeam(team);

        return activityRepository.save(activity);
    }



    public Activity updateActivity(Long id, Activity updatedActivity) {
        Activity activity = activityRepository.findById(id).get();
        activity.setActivityName(updatedActivity.getActivityName());
        activity.setDescriptionA(updatedActivity.getDescriptionA());
        activity.setObjectiveA(updatedActivity.getObjectiveA());
        activity.setDurationA(updatedActivity.getDurationA());
        activity.setDeadlineA(updatedActivity.getDeadlineA());
        return activityRepository.save(activity);
    }


    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
