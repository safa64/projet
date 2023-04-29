package com.example.projectmanagement.Domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_activity")
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "activity_seq")
    private Long id;
    private String activityName;
    private String descriptionA;
    private String  objectiveA;
    private String durationA;
    private Date deadlineA ;

    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
