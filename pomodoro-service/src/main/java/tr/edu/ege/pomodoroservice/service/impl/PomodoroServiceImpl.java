package tr.edu.ege.pomodoroservice.service.impl;

import org.springframework.stereotype.Service;
import tr.edu.ege.pomodoroservice.model.Pomodoro;
import tr.edu.ege.pomodoroservice.repository.PomodoroRepository;
import tr.edu.ege.pomodoroservice.service.PomodoroService;

import javax.persistence.Convert;

@Service
public class PomodoroServiceImpl implements PomodoroService {
    private final PomodoroRepository pomodoroRepository;

    public PomodoroServiceImpl(PomodoroRepository pomodoroRepository) {
        this.pomodoroRepository = pomodoroRepository;
    }

    @Override
    public Pomodoro save(Pomodoro pomodoro) {
        return null;
    }

    @Override
    public void updatePomodoro(Long pomodoroId,int completedMinute) {
        final Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId).orElseThrow();
        int pomodoroCompleted = pomodoro.getCompleted();
        int pomodoroEst = pomodoro.getEstimate();
        pomodoro.setCompleted(pomodoroCompleted+1);

        if(pomodoroEst!=0){
            pomodoro.setEstimate(pomodoroEst-1);
        }
        pomodoro.setCompletedPomodoroTime(pomodoro.getCompletedPomodoroTime()+completedMinute);
    }

    @Override
    public int totalTimeForPomodoro(Long pomodoroId) {
        final Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId).orElseThrow();
        return pomodoro.getCompletedPomodoroTime();
    }

    @Override
    public void updatePomodoroBreak(Long pomodoroId, int pomodoroBreak) {
        final Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId).orElseThrow();
        pomodoro.setBreakPomodoroTime(pomodoro.getBreakPomodoroTime()+pomodoroBreak);
    }

    @Override
    public int totalTimeForPomodoroBreak(Long pomodoroId) {
        return pomodoroRepository.findById(pomodoroId).orElseThrow().getBreakPomodoroTime();
    }

}
