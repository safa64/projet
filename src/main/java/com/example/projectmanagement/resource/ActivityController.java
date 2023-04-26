package com.example.projectmanagement.resource;

import com.example.projectmanagement.Domaine.Activity;
import com.example.projectmanagement.Service.ActivityImplServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityImplServ activityService;

    @GetMapping("/getActivityByProjectId/{id}")
public List<Activity> getActivityByProjectId(@PathVariable Long id) {
        return activityService.getActivityByProjectId(id);

    }

    @GetMapping("/getActivityById/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return  activityService.getActivityById(id);

    }

    @PostMapping("/createActivity")
    public Activity createActivity(@RequestBody Activity activity) {
        return   activityService.createActivity(activity);

    }

    @PutMapping("/updateActivity/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity updatedActivity) {
        return  activityService.updateActivity(id, updatedActivity);
    }

    @DeleteMapping("/deleteActivity/{idu}")
    public void deleteActivity(@PathVariable("idu") Long idUser)
    {
     activityService.deleteActivity(idUser);
    }

}
