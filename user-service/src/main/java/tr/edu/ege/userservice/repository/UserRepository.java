package tr.edu.ege.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ege.userservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
