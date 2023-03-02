package it.paganello.pagapp.repositories;

import java.util.Optional;

import it.paganello.pagapp.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
	Optional<Tournament> findByName(final String name);
}
