package it.paganello.pagapp.repositories;

import java.util.Optional;

import it.paganello.pagapp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("SELECT t FROM Team t WHERE (t.name = ?1 AND t.tournament.id = ?2)")
	Optional<Team> findByNameAndTournamentId(final String name, final Long tournamentId);
}
