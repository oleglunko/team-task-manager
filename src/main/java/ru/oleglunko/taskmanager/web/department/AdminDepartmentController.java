package ru.oleglunko.taskmanager.web.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.oleglunko.taskmanager.model.Department;
import ru.oleglunko.taskmanager.service.DepartmentService;
import ru.oleglunko.taskmanager.web.SecurityUtil;

import javax.validation.Valid;

import java.net.URI;

import static ru.oleglunko.taskmanager.util.ValidationUtil.assureIdConsistent;
import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNew;

@Slf4j
@Validated
@RestController
@RequestMapping(value = AdminDepartmentController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDepartmentController {

    static final String REST_URL = "/rest/admin/departments";

    private final DepartmentService service;

    public AdminDepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> create(@Valid @RequestBody Department department) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("create department {} by admin {}", department, employeeId);
        checkNew(department);
        Department created = service.create(department);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Department department, @PathVariable int id) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("update department {} by admin {}", id, employeeId);
        assureIdConsistent(department, id);
        service.update(department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("delete department {} by admin {}", id, employeeId);
        service.delete(id);
    }
}
