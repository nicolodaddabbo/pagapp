package it.paganello.pagapp.match;

import java.util.Optional;

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

    @PutMapping(value = "/{id}")
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Match>> getMatch(@PathVariable Long id) {
        Optional<Match> match = service.getMatchById(id);
        if (match.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
}
