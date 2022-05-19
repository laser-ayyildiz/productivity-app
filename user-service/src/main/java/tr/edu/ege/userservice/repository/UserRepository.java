package tr.edu.ege.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ege.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
