package it.paganello.pagapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.paganello.pagapp.dto.MatchDto;
import it.paganello.pagapp.dto.RoundDto;
import it.paganello.pagapp.dto.TournamentDto;
import it.paganello.pagapp.entities.Tournament;
import it.paganello.pagapp.repositories.TournamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.entities.Round;

@Service
public class TournamentService {
	@Autowired
	TournamentRepository repository;
	@Autowired
	RoundService roundService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private boolean isNameTaken(final String name) {
		return repository.findByName(name).isPresent();
	}
	
	public Optional<String> createTournament(final String name) {
		if (isNameTaken(name)) {
			return Optional.of("Name already taken");
		}
		repository.save(new Tournament(name));
		return Optional.empty();
	}
	
	public TournamentDto getTournamentById(final Long id) {
		Optional<Tournament> tournament = repository.findById(id);
		
		if(tournament.isEmpty()){
			return null;
		}
		
		TournamentDto response = modelMapper.map(tournament.get(), TournamentDto.class);
		
		return response;
	}
	
	public List<TournamentDto> getTournaments() {
		List<Tournament> tournamentList = repository.findAll();
		
		List<TournamentDto> response = new ArrayList<>();
		
		tournamentList.stream().forEach(t -> {
			TournamentDto dto = modelMapper.map(t, TournamentDto.class);
			response.add(dto);
		});
		
		return response;
	}
	
	public RoundDto getRoundByRoundNumber(final Long id, final int roundNumber) {
		Optional<Tournament> tournament = repository.findById(id);
		if (tournament.isEmpty()) {
			return null;
		}
		roundService.isRoundOver(tournament.get(), tournament.get().getRounds());
		repository.save(tournament.get());
		Optional<Round> round = tournament.get().getRounds().stream()
				.filter(r -> r.getRoundNumber() == roundNumber)
				.findAny();
		
		return convertRoundToDto(round.get());
	}
	
	
	
	public List<RoundDto> getRounds(final Long tournamentId) {
		Optional<Tournament> tournament = repository.findById(tournamentId);
		if (tournament.isEmpty()) {
			return null;
		}
		roundService.isRoundOver(tournament.get(), tournament.get().getRounds());
		repository.save(tournament.get());
		
		List<RoundDto> roundsDto = tournament.get().getRounds().stream().map(r -> convertRoundToDto(r)).toList();
		
		return roundsDto;
	}
	
	public Optional<String> computeRound(final Long tournamentId, final String matchingAlgorithm) {
		Optional<Tournament> tournament = repository.findById(tournamentId);
		if (tournament.isEmpty()) {
			return Optional.of("Tournament not found");
		}
		return roundService.computeRound(tournament.get(), matchingAlgorithm).isEmpty() ? Optional.of("Round NOT created") : Optional.of("Round created");
	}
	
	public RoundDto getCurrentRoundByTournamentId(final Long id) {
		Optional<Tournament> tournament = repository.findById(id);
		if (tournament.isEmpty()) {
			return null;
		}
		return getRoundByRoundNumber(id, tournament.get().getCurrentRoundNumber());
	}
	
	public Optional<String> isCurrentRoundFinished(final Long id) {
		Optional<Tournament> tournament = repository.findById(id);
		if (tournament.isEmpty()) {
			return Optional.empty();
		}
		roundService.isRoundOver(tournament.get(), tournament.get().getRounds());
		repository.save(tournament.get());
		return Optional.of(
				tournament.get()
						.getRounds()
						.get(tournament.get().getCurrentRoundNumber())
						.isFinished() ? "Finished" : "NOT Finished");
	}
	
	private RoundDto convertRoundToDto(Round round) {
		RoundDto roundDto = modelMapper.map(round, RoundDto.class);
		roundDto.setTournament(round.getTournament().getName());
		List<MatchDto> matchesDto = round.getMatches().stream()
				.map(m -> {
					MatchDto mDto = modelMapper.map(m, MatchDto.class);
					mDto.setHomeTeamName(m.getHomeTeam().getName());
					mDto.setAwayTeamName(m.getAwayTeam().getName());
					mDto.setHomeTeamPoints(m.getHomeTeamPoints());
					mDto.setAwayTeamPoints(m.getAwayTeamPoints());
					return mDto;
				}).collect(Collectors.toList());
		roundDto.setMatches(matchesDto);
		return roundDto;
	}
	
}
