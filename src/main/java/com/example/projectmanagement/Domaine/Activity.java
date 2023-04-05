package com.example.projectmanagement.Domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_activity")
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "usr_seq")
    private Long id;
    private String activityName;
    private String descriptionA;
    private String  objectiveA;
    private String durationA;
    private Date deadlineA ;

    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
