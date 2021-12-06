package ru.oleglunko.taskmanager.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oleglunko.taskmanager.model.Department;
import ru.oleglunko.taskmanager.service.DepartmentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = DepartmentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    static final String REST_URL = "/rest/departments";

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> getAll() {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("getAll for employee {}", employeeId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable int id) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("get company {} for employee {}", id, employeeId);
        return service.get(id);
    }
}
