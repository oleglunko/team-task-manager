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
@RequestMapping(value = DepartmentEmployeeController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentEmployeeController {

    static final String REST_URL = "/rest/departments/{departmentId}/employees";

    private final EmployeeService service;

    public DepartmentEmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllInDepartment(@PathVariable int departmentId) {
        log.info("get all employees in the department {}", departmentId);
        return service.getAllByDepartmentId(departmentId);
    }
}
