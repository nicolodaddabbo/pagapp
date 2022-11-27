package it.paganello.pagapp.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TeamController {
    @Autowired
    TeamService service;

    @PostMapping("/{id}/team")
    public Team addTeam(@PathVariable Long id, @RequestBody Team newTeam) {
        return service.addTeam(id, newTeam);
    }

    @PutMapping("/{tournamentId}/team/{teamId}")
    public Optional<Team> updateTeam(@PathVariable Long tournamentId, @PathVariable Long teamId, @RequestBody Team newTeam) {
        return service.updateTeam(tournamentId, teamId, newTeam);
    }
    
}
