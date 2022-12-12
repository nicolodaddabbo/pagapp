package it.paganello.pagapp.tournament;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.round.Round;
import it.paganello.pagapp.round.RoundService;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository repository;
    @Autowired
    RoundService roundService;

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

    public Optional<Tournament> getTournamentById(final Long id) {
        return repository.findById(id);
    }

    public List<Tournament> getTournaments() {
        return repository.findAll();
    }

    public Optional<Round> getRoundByRoundNumber(final Long id, final int roundNumber) {
        Optional<Tournament> tournmanent = getTournamentById(id);
        if (tournmanent.isEmpty()) {
            return Optional.empty();
        }
        return tournmanent.get().getRounds().stream()
                .filter(r -> r.getRoundNumber() == roundNumber)
                .findAny();
    }

    public Optional<List<Round>> getRounds(final Long tournamentId) {
        Optional<Tournament> tournmanent = getTournamentById(tournamentId);
        if (tournmanent.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(tournmanent.get().getRounds());
    }

    public Optional<String> computeRound(final Long tournamentId, final String matchingAlgorithm) {
        Optional<Tournament> tournament = getTournamentById(tournamentId);
        if (tournament.isEmpty()) {
            return Optional.of("Tournament not found");
        }
        return roundService.computeRound(tournament.get(), matchingAlgorithm).isEmpty() ? Optional.of("Round NOT created") : Optional.of("Round created");
    }

    public Optional<Round> getCurrentRoundByTournamentId(final Long id) {
        Optional<Tournament> tournmanent = getTournamentById(id);
        if (tournmanent.isEmpty()) {
            return Optional.empty();
        }
        return getRoundByRoundNumber(id, tournmanent.get().getCurrentRoundNumber());
    }

    public Optional<String> isCurrentRoundFinished(final Long id) {
        Optional<Tournament> tournament = getTournamentById(id);
        if (tournament.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(
                tournament.get()
                .getRounds()
                .get(tournament.get().getCurrentRoundNumber())
                .isFinished() ? "Finished" : "NOT Finished");
    }

    public Optional<Round> firstRound(final Long id, final Round round) {
        Optional<Tournament> tournament = getTournamentById(id);
        if (tournament.isEmpty() || !tournament.get().getRounds().isEmpty()) {
            return Optional.empty();
        }
        Round firstRound = roundService.firstRound(tournament.get(), round);
        tournament.get().getRounds().add(firstRound);
        return Optional.of(firstRound);
    }
}
