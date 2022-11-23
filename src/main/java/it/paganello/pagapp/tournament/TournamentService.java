package it.paganello.pagapp.tournament;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;

    public Tournament createTournament(final Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament getTournamentById(final Long id) {
        return tournamentRepository.findById(id)
            .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }
}
