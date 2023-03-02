package it.paganello.pagapp.services;

import java.util.Optional;

import it.paganello.pagapp.dto.MatchDto;
import it.paganello.pagapp.entities.Match;
import it.paganello.pagapp.repositories.MatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	MatchRepository repository;
	
	public Optional<String> updateMatchResult(final Long id, final int homeTeamPoints, final int awayTeamPoints) {
		Optional<Match> match = repository.findById(id);
		if (match.isEmpty()) {
			return Optional.of("Match not found");
		}
		match.get().setHomeTeamPoints(homeTeamPoints);
		match.get().setAwayTeamPoints(awayTeamPoints);
		match.get().setFinished(true);
		repository.save(match.get());
		return Optional.empty();
	}
	
	public Match createMatch(final Match match) {
		return repository.save(match);
	}
	
	public MatchDto getMatchById(final Long id) {
		Optional<Match> match = repository.findById(id);
		
		if(match.isEmpty()){
			return null;
		}else{
			MatchDto response = modelMapper.map(match, MatchDto.class);
			return response;
		}
	}
}
