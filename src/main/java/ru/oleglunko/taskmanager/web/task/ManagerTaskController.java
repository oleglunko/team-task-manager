package ru.oleglunko.taskmanager.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.oleglunko.taskmanager.model.Task;
import ru.oleglunko.taskmanager.service.TaskService;
import ru.oleglunko.taskmanager.web.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.oleglunko.taskmanager.util.ValidationUtil.assureIdConsistent;

@Slf4j
@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping(value = ManagerTaskController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerTaskController {

    static final String REST_URL = "/rest/manager/tasks";

    private final TaskService service;

    public ManagerTaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> create(@Valid @RequestBody Task task) {
        int managerId = SecurityUtil.authEmployeeId();
        log.info("create {} by manager {}", task, managerId);
        int performerId = task.getPerformer().getId();
        Task created = service.create(task, managerId, performerId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Task task, @PathVariable int id) {
        int managerId = SecurityUtil.authEmployeeId();
        log.info("update {} by manager {}", task, managerId);
        assureIdConsistent(task, id);
        int performerId = task.getPerformer().getId();
        service.update(task, managerId, performerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int managerId = SecurityUtil.authEmployeeId();
        log.info("delete {} by manager {}", id, managerId);
        service.delete(id, managerId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptOrDecline(@PathVariable int id, @RequestParam boolean accepted) {
        int managerId = SecurityUtil.authEmployeeId();
        log.info(accepted ? "accept {} by manager {}" : "decline {} by manager {}", id, managerId);
        service.acceptOrDecline(id, managerId, accepted);
    }
}
