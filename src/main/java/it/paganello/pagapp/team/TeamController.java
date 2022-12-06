package it.paganello.pagapp.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TeamController {
    @Autowired
    TeamService service;

    @GetMapping("/{tournamentId}/team")
    public ResponseEntity<Optional<Team>> getTeamByName(@PathVariable final Long tournamentId, @RequestParam final String name) {
        Optional<Team> team = service.getTeamByName(name, tournamentId);
        if (team.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/{tournamentId}/team")
    public ResponseEntity<String> createTeam(
                @PathVariable final Long tournamentId, 
                @RequestParam final String name,
                @RequestParam final int seed) {
        Optional<String> error = service.createTeam(tournamentId, name, seed);
        if (error.isPresent()) {
            return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Team " + name + " created", HttpStatus.OK);
    }

    @PutMapping("/{tournamentId}/team/{teamId}")
    public ResponseEntity<String> updateTeam(
            @PathVariable final Long tournamentId, 
            @PathVariable final Long teamId, 
            @RequestParam final String newName,
            @RequestParam final int newSeed) {
        Optional<String> error = service.updateTeam(tournamentId, teamId, newName, newSeed);
        if (error.isPresent()) {
            return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Team " + newName + " updated", HttpStatus.OK);
    }
    
}
