package it.paganello.pagapp.tournament;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.paganello.pagapp.round.Round;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository repository;

    public Tournament createTournament(final Tournament tournament) {
        return repository.save(tournament);
    }

    public Tournament getTournamentById(final Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public List<Tournament> getTournaments() {
        return repository.findAll();
    }

    public Optional<Round> getRoundByRoundNumber(final Long id, final int roundNumber) {
        try {
            Tournament tournament = getTournamentById(id);
            return tournament.getRounds().stream()
                    .filter(r -> r.getRoundNumber() == roundNumber)
                    .findAny();
        } catch (TournamentNotFoundException e) {
            throw new TournamentNotFoundException(id);
        }
    }

    public Optional<Round> getCurrentRoundByTournamentId(final Long id) {
        try {
            Tournament tournament = getTournamentById(id);
            return getRoundByRoundNumber(id, tournament.getCurrentRoundNumber());
        } catch (TournamentNotFoundException e) {
            throw new TournamentNotFoundException(id);
        }
    }

    public boolean isCurrentRoundFinished(final Long id) {
        Tournament tournament = repository.findById(id).get();
        if (tournament == null) {
            throw new TournamentNotFoundException(id);
        }
        return tournament.getRounds().get(tournament.getCurrentRoundNumber()).isFinished();
    }
}
