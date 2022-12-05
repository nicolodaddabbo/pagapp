package it.paganello.pagapp.tournament;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paganello.pagapp.round.Round;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {
    @Autowired
    TournamentService service;
    
    @PostMapping
    public ResponseEntity<String> createTournament(@RequestBody final String name) {
        Optional<String> error = service.createTournament(name);
        if (error.isPresent()) {
            return new ResponseEntity<>(error.get(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tournament " + name + " created", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getTournaments() {
        return new ResponseEntity<>(service.getTournaments(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tournament>> getTournamentById(@PathVariable final Long id) {
        Optional<Tournament> tournament = service.getTournamentById(id);
        if (tournament.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tournament, HttpStatus.OK);
    }

    @GetMapping("/{id}/currentRound")
    public ResponseEntity<Optional<Round>> getCurrentRoundByTournamentId(@PathVariable final Long id) {
        Optional<Round> round = service.getCurrentRoundByTournamentId(id);
        if (round.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @GetMapping("/{id}/rounds/{roundNumber}")
    public ResponseEntity<Optional<Round>> getRoundByRoundNumber(@PathVariable final Long id, @PathVariable final int roundNumber) {
        Optional<Round> round = service.getRoundByRoundNumber(id, roundNumber);
        if (round.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(round, HttpStatus.OK);
    }

    @GetMapping("/{id}/isCurrentRoundFinished")
    public ResponseEntity<String> isCurrentRoundFinished(@PathVariable final Long id) {
        Optional<String> response = service.isCurrentRoundFinished(id);
        if (response.isEmpty()) {
            return new ResponseEntity<>("Tournament not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }
    
    @PostMapping("/{id}/firstRound")
    public Optional<Round> firstRound(@PathVariable Long id, @RequestBody Round round) {
        return service.firstRound(id, round);
    }

}
