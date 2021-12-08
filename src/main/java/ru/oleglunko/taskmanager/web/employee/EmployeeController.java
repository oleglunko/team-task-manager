package ru.oleglunko.taskmanager.web.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oleglunko.taskmanager.model.Employee;
import ru.oleglunko.taskmanager.service.EmployeeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = EmployeeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    static final String REST_URL = "/rest/employees";

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        log.info("get all employees");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable int id) {
        log.info("get employee {}", id);
        return service.get(id);
    }
}
