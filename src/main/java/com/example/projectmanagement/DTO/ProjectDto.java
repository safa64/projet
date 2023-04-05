package com.example.projectmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String projectName;
    private String descriptionP;
    private String ObjectiveP;
    private String durationP;
    private Date deadlineP;
    private Long adminId;
    private Long teamLeaderId;
    private List<ActivityDto> activities = new ArrayList<>();
}
