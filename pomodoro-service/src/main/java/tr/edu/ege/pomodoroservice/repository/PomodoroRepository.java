package tr.edu.ege.pomodoroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.ege.pomodoroservice.model.Pomodoro;

@Repository
public interface PomodoroRepository extends JpaRepository<Pomodoro,Long> {
}
