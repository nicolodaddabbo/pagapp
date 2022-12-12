package it.paganello.pagapp.tournament;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paganello.pagapp.match.MatchDto;
import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.round.RoundDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService service;

    @Autowired
    private ModelMapper modelMapper;
    
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
    public ResponseEntity<Optional<RoundDto>> getRoundByRoundNumber(@PathVariable final Long id, @PathVariable final int roundNumber) {
        Optional<Round> round = service.getRoundByRoundNumber(id, roundNumber);
        if (round.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        RoundDto roundDto = convertToDto(round.get());
        return new ResponseEntity<>(Optional.of(roundDto), HttpStatus.OK);
    }

    @GetMapping("/{tournamentId}/rounds")
    public ResponseEntity<Optional<List<RoundDto>>> getRounds(@PathVariable final Long tournamentId) {
        Optional<List<Round>> rounds = service.getRounds(tournamentId);
        if (rounds.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }
        List<RoundDto> roundsDto = rounds.get().stream().map(r -> convertToDto(r)).toList();

        return new ResponseEntity<>(Optional.of(roundsDto), HttpStatus.OK);
    }

    private RoundDto convertToDto(Round round) {
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
