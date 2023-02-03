package it.paganello.pagapp.repositories;

import it.paganello.pagapp.entities.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
    
}
