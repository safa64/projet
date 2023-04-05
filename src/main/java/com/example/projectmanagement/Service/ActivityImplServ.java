package com.example.projectmanagement.Service;

import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Domaine.Task;
import com.example.projectmanagement.Domaine.User;
import com.example.projectmanagement.Reposirtory.ActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityImplServ implements ActitvtyServ{
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivityByProjectId(Long id) {
        return activityRepository.findByProjectId(id);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Activity createActivity(Activity activity) {
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
