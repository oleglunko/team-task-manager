package ru.oleglunko.taskmanager.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.oleglunko.taskmanager.model.Task;
import ru.oleglunko.taskmanager.service.TaskService;
import ru.oleglunko.taskmanager.web.SecurityUtil;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = TaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    static final String REST_URL = "/rest/tasks";

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("get all in department for employee {}", employeeId);
        return service.getAllInEmployeesDepartment(employeeId);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable int id) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("get {} for employee {}", id, employeeId);
        return service.getInEmployeesDepartment(id, employeeId);
    }

    @GetMapping("/my")
    public List<Task> getAllForPerformer() {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("get all for performer {}", employeeId);
        return service.getAllForPerformer(employeeId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void perform(@PathVariable int id) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("perform {} by employee {}", id, employeeId);
        service.perform(id, employeeId);
    }
}
