package tr.edu.ege.pomodoroservice.service;

import tr.edu.ege.pomodoroservice.model.Pomodoro;

public interface PomodoroService {
    Pomodoro save(Pomodoro pomodoro);

    void updatePomodoro(Long pomodoroId,int minute);

    int totalTimeForPomodoro(Long pomodoroId);

    void updatePomodoroBreak(Long pomodoroId, int pomodoroBreak);

    int totalTimeForPomodoroBreak(Long pomodoroId);
}
