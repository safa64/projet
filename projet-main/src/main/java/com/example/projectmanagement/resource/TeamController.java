package com.example.projectmanagement.resource;

import com.example.projectmanagement.Domaine.Team;
import com.example.projectmanagement.Service.TeamImplServ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class TeamController {


    @Autowired
    private TeamImplServ teamService;
    @GetMapping(value = "/getAllTeam")
    public List<Team> getAllTeam() {
        return teamService.getAllTeam();
    }
    @PreAuthorize("hasAuthority('ADMIN')and hasAuthority('Manager')")
    @PostMapping(value = "/addTeam")
    public Team addTeam(@RequestBody Team team)
    {
        return teamService.addTeam(team);
    }

    @PutMapping(value = "/updateTeam/{idteam}")
    public Team updateTeam(@RequestBody  Team team, @PathVariable("idteam") Long idTeam)
    {
        return teamService.updateTeam(team, idTeam);
    }
    @PreAuthorize("hasAuthority('ADMIN')and hasAuthority('Manager')")
    @DeleteMapping(value = "/deleteTeam/{idTeam}")
    public void deleteTeam(@PathVariable("idTeam") Long idTeam)
    {
        teamService.deleteTeam(idTeam);
    }


    @GetMapping("/GetTeamById/{idTeam}")
    public Team findById(@PathVariable("idTeam") Long idTeam) {
        return teamService.findById(idTeam);
    }

}
