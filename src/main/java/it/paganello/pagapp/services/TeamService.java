package it.paganello.pagapp.services;

import java.util.Optional;

import it.paganello.pagapp.dto.TeamDto;
import it.paganello.pagapp.entities.Team;
import it.paganello.pagapp.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.entities.Tournament;
import it.paganello.pagapp.repositories.TournamentRepository;

@Service
public class TeamService {
	@Autowired
	TeamRepository teamRepository;
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TeamDto getTeamByName(final String name, final Long tournamentId) {
		Optional<Team> team = teamRepository.findByNameAndTournamentId(name, tournamentId);
		
		if(team.isEmpty()){
			return  null;
		}else{
			TeamDto response = modelMapper.map(team, TeamDto.class);
			return response;
		}
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
	
	public Optional<String> updateTeam(final Long teamId, final String newName, final int newSeed) {
		Optional<Team> team = teamRepository.findById(teamId);
		if (team.isEmpty()) {
			return Optional.of("Team not found");
		}
		if (isNameTaken(newName, team.get().getTournament().getId())) {
			return Optional.of("Team name already taken");
		}
		team.get().setName(newName);
		team.get().setSeed(newSeed);
		teamRepository.save(team.get());
		return Optional.empty();
	}
}
