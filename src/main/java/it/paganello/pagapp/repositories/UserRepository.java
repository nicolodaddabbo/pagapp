package it.paganello.pagapp.repositories;

import it.paganello.pagapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
