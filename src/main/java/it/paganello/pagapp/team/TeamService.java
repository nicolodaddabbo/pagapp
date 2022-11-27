package it.paganello.pagapp.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.tournament.TournamentRepository;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    public Team addTeam(final Long id, final Team newTeam) {
        Team team = tournamentRepository.findById(id).map(tournament -> {
            newTeam.setTournament(tournament);
            return teamRepository.save(newTeam);
        }).orElseThrow();
        return team;
    }
}
