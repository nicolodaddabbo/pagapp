package it.paganello.pagapp.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.tournament.TournamentNotFoundException;
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

    public Optional<Team> updateTeam(final Long tournamentId, final Long teamId, final Team newTeam) {
        tournamentRepository.findById(tournamentId).map(tournament -> {
            teamRepository.findById(teamId).map(team ->{
                team.setName(newTeam.getName());
                team.setTournament(tournament);
                return teamRepository.save(team);
            }).orElseThrow();
            return addTeam(tournamentId, newTeam);
        }).orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        return Optional.empty();
    }
}
