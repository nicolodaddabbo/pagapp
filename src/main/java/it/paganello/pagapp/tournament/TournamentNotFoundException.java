package it.paganello.pagapp.tournament;

public class TournamentNotFoundException extends RuntimeException {
    TournamentNotFoundException(final Long id) {
        super("Could not find Tournament " + id);
    }
}
