package it.paganello.pagapp.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.tournament.Tournament;
import it.paganello.pagapp.tournament.TournamentRepository;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    public Optional<Team> getTeamByName(final String name, final Long tournamentId) {
        return teamRepository.findByNameAndTournamentId(name, tournamentId);
    }

    private boolean isNameTaken(final String name, final Long tournamentId) {
        return teamRepository.findByNameAndTournamentId(name, tournamentId).isPresent();
    }

    public Optional<String> createTeam(final Long tournamentId, final String name, final int seed) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            return Optional.of("Tournament not found");
        }
        if (isNameTaken(name, tournamentId)) {
            return Optional.of("Team name already taken");
        }
        Team team = new Team(name, seed);
        team.setTournament(tournament.get());
        teamRepository.save(team);
        return Optional.empty();
    }

    public Optional<String> updateTeam(final Long tournamentId, final Long teamId, final String newName, final int newSeed) {
        Optional<Tournament> tournament = tournamentRepository.findById(tournamentId);
        if (tournament.isEmpty()) {
            return Optional.of("Tournament not found");
        }
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            return Optional.of("Team not found");
        }
        if (isNameTaken(newName, tournamentId)) {
            return Optional.of("Team name already taken");
        }
        team.get().setName(newName);
        team.get().setSeed(newSeed);
        teamRepository.save(team.get());
        return Optional.empty();
    }
}
