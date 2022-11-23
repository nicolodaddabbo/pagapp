package it.paganello.pagapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.paganello.pagapp.team.Team;
import it.paganello.pagapp.tournament.Tournament;
import it.paganello.pagapp.tournament.TournamentService;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    
    private List<Team> initalTeams = new ArrayList<>(
        Arrays.asList(
            new Team("team1"),
            new Team("team2")
        )
    );

    @Bean
    public CommandLineRunner initDatabase(TournamentService service) {
        return args -> {
            log.info("Preloading " + service.createTournament(new Tournament("Paga", initalTeams)));
        };
    }
}
