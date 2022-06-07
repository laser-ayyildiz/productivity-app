package tr.edu.ege.pomodoroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.edu.ege.pomodoroservice.model.Pomodoro;
import tr.edu.ege.pomodoroservice.service.PomodoroService;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.version}/pomodoro")
public class PomodoroController {
    private final PomodoroService pomodoroService;

    @PostMapping
    public Pomodoro create(@RequestBody Pomodoro pomodoro) {
        return pomodoroService.save(pomodoro);
    }

    @PutMapping
    public void updatePomodoro(@RequestParam Long pomodoroId,
                               @RequestParam int minute) {
        pomodoroService.updatePomodoro(pomodoroId, minute);
    }

    @GetMapping
    public int totalTimeForPomodoro(@RequestParam Long pomodoroId) {
        return pomodoroService.totalTimeForPomodoro(pomodoroId);
    }

    @PutMapping
    public void updatePomodoroBreak(@RequestParam Long pomodoroId,
                             @RequestParam int pomodoroBreak) {
        pomodoroService.updatePomodoroBreak(pomodoroId,pomodoroBreak);
    }

    @GetMapping
    public int totalTimeForPomodoroBreak(@RequestParam Long pomodoroId) {
        return pomodoroService.totalTimeForPomodoroBreak(pomodoroId);
    }
}
