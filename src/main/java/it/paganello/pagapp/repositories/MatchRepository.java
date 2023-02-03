package it.paganello.pagapp.repositories;

import it.paganello.pagapp.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    
}
