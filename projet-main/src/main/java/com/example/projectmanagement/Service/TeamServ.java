package com.example.projectmanagement.Service;

import com.example.projectmanagement.Domaine.Team;

import java.util.List;

public interface TeamServ {
    public List<Team> getAllTeam();
    public Team addTeam(Team team);
    public Team updateTeam(Team team, Long idTeam);
    public void deleteTeam(Long idTeam);
    public Team findById(Long id);
}
