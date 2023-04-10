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
@Table(name = "_project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "usr_seq")
    private Long id;
    private String projectName;
    private String descriptionP;
    private String ObjectiveP ;
    private String durationP;
    private Date deadlineP ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_leader_id")
    private User teamLeader;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Activity> activity = new ArrayList<>();
}