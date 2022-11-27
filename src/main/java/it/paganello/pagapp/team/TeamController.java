package it.paganello.pagapp.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.paganello.pagapp.tournament.TournamentRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TeamController {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    @PostMapping(value="/{id}/team")
    public Team postMethodName(@PathVariable Long id, @RequestBody Team newTeam) {
        Team team = tournamentRepository.findById(id).map(tournament -> {
            newTeam.setTournament(tournament);
            return teamRepository.save(newTeam);
        }).orElseThrow();
        return team;
    }
    
}
