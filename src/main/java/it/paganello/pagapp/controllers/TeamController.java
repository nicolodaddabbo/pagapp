package it.paganello.pagapp.controllers;

import java.util.Optional;

import it.paganello.pagapp.dto.TeamDto;
import it.paganello.pagapp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/team")
public class TeamController {
	@Autowired
	TeamService service;
	
	@GetMapping("/{name}/{tournamentId}")
	public ResponseEntity<TeamDto> getTeamByName(@PathVariable final Long tournamentId, @PathVariable final String name) {
		TeamDto team = service.getTeamByName(name, tournamentId);
		if (team == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(team, HttpStatus.OK);
	}
	
	@PostMapping("/create/{tournamentId}")
	public ResponseEntity<String> createTeam(@PathVariable final Long tournamentId, @RequestParam final String name, @RequestParam final int seed) {
		Optional<String> error = service.createTeam(tournamentId, name, seed);
		if (error.isPresent()) {
			return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Team " + name + " created", HttpStatus.OK);
	}
	
	@PutMapping("/update/{teamId}")
	public ResponseEntity<String> updateTeam(@PathVariable final Long teamId, @RequestParam final String newName, @RequestParam final int newSeed) {
		Optional<String> error = service.updateTeam(teamId, newName, newSeed);
		if (error.isPresent()) {
			return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Team " + newName + " updated", HttpStatus.OK);
	}
	
}
