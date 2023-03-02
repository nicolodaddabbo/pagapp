package it.paganello.pagapp.controllers;

import java.util.Optional;

import it.paganello.pagapp.dto.MatchDto;
import it.paganello.pagapp.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/match")
public class MatchController {
	@Autowired
	MatchService service;
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<String> updateMatchResult(
			@PathVariable final Long id,
			@RequestParam final int homeTeamPoints,
			@RequestParam final int awayTeamPoints) {
		Optional<String> error = service.updateMatchResult(id, homeTeamPoints, awayTeamPoints);
		if (error.isPresent()) {
			return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Match results updated", HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<MatchDto> getMatch(@PathVariable Long id) {
		MatchDto match = service.getMatchById(id);
		if (match == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(match, HttpStatus.OK);
	}
}
