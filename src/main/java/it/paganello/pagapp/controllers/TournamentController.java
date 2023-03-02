package it.paganello.pagapp.controllers;

import it.paganello.pagapp.dto.RoundDto;
import it.paganello.pagapp.dto.TournamentDto;
import it.paganello.pagapp.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/tournament")
public class TournamentController {
	@Autowired
	private TournamentService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> createTournament(@RequestBody final String name) {
		Optional<String> error = service.createTournament(name);
		if (error.isPresent()) {
			return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tournament " + name + " created", HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<TournamentDto>> getTournaments() {
		return new ResponseEntity<>(service.getTournaments(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<TournamentDto> getTournamentById(@PathVariable final Long id) {
		TournamentDto tournament = service.getTournamentById(id);
		if (tournament == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tournament, HttpStatus.OK);
	}
	
	@GetMapping("/currentRound/{id}")
	public ResponseEntity<RoundDto> getCurrentRoundByTournamentId(@PathVariable final Long id) {
		RoundDto round = service.getCurrentRoundByTournamentId(id);
		if (round == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(round, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/rounds/{roundNumber}")
	public ResponseEntity<RoundDto> getRoundByRoundNumber(@PathVariable final Long id, @PathVariable final int roundNumber) {
		RoundDto round = service.getRoundByRoundNumber(id, roundNumber);
		if (round == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(round, HttpStatus.OK);
	}
	
	@GetMapping("/rounds/{id}")
	public ResponseEntity<List<RoundDto>> getRounds(@PathVariable final Long id) {
		
		List<RoundDto> rounds = service.getRounds(id);
		
		if (rounds.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(rounds, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{id}/isCurrentRoundFinished")
	public ResponseEntity<String> isCurrentRoundFinished(@PathVariable final Long id) {
		Optional<String> response = service.isCurrentRoundFinished(id);
		if (response.isEmpty()) {
			return new ResponseEntity<>("Tournament not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response.get(), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/computeRound")
	public Optional<String> computeRound(@PathVariable final Long id, @RequestBody String matchingAlgorithm) {
		return service.computeRound(id, matchingAlgorithm);
	}
	
}
