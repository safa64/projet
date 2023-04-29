package com.example.projectmanagement.Service;

import com.example.projectmanagement.DTO.ActivityDto;
import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Domaine.Authorisation;

import java.util.List;

public interface ActitvtyServ {

    public List<Activity> getAllActivity();
    public List<Activity> getActivityByProjectId(Long id);
    public Activity updateActivity(Long id, Activity updatedActivity);
    public void deleteActivity(Long id);
    public Activity createActivity(ActivityDto activityDto);
    public Activity getActivityById(Long id);
}
