package com.example.projectmanagement.Domaine;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_Team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_seq")
    @SequenceGenerator(name = "task_seq",sequenceName = "task_seq")
    private Long TeamId;
    @Column(name = "team_name")
    private String TeamName;
    private String TeamDesc;
    @OneToMany(mappedBy = "team")
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "team_user",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> Team = new HashSet<>();
}
