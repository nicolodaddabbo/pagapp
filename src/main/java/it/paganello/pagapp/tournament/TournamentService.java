package it.paganello.pagapp.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;

    public void createTournament(final Tournament tournament) {
        tournamentRepository.save(tournament);
    }
}
