package com.example.projectmanagement.Service;

import com.example.projectmanagement.Domaine.Project;
import com.example.projectmanagement.Domaine.Team;
import com.example.projectmanagement.Reposirtory.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamImplServ implements TeamServ{
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

    public Team addTeam(Team team) {
        // TODO Auto-generated method stub
        return teamRepository.save(team);
    }
    public Team updateTeam(Team team, Long idTeam) {
        // TODO Auto-generated method stub

        Team newteam= teamRepository.findById(idTeam).get();

        newteam.setTeamName(newteam.getTeamName());
        newteam.setTeamDesc(newteam.getTeamDesc());
        return teamRepository.save(newteam);
    }
    public void deleteTeam(Long idTeam) {
        // TODO Auto-generated method stub

        teamRepository.deleteById(idTeam);

    }


    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

}
