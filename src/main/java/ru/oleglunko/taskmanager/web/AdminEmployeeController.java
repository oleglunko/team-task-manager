package ru.oleglunko.taskmanager.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import ru.oleglunko.taskmanager.model.Employee;
import ru.oleglunko.taskmanager.service.EmployeeService;

import javax.validation.Valid;
import java.net.URI;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNew;

@Slf4j
@RestController
@RequestMapping(value = AdminEmployeeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminEmployeeController {

    static final String REST_URL = "/rest/admin/employees";

    private final EmployeeService service;

    public AdminEmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        int adminId = SecurityUtil.authEmployeeId();
        log.info("create employee {} by admin {}", employee, adminId);
        checkNew(employee);
        Employee created = service.create(employee, employee.getDepartment().getId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Employee employee) {
        int adminId = SecurityUtil.authEmployeeId();
        log.info("update employee {} by admin {}", employee, adminId);
        service.update(employee, employee.getDepartment().getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int adminId = SecurityUtil.authEmployeeId();
        log.info("delete {} by admin {}", id, adminId);
        service.delete(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        int adminId = SecurityUtil.authEmployeeId();
        log.info(enabled ? "enable {} by admin {}" : "disable {} by admin {}", id, adminId);
        service.enable(id, enabled);
    }
}
