package ru.oleglunko.taskmanager.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oleglunko.taskmanager.model.Company;
import ru.oleglunko.taskmanager.service.CompanyService;

@Slf4j
@RestController
@RequestMapping(value = CompanyController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    static final String REST_URL = "/rest/company";

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public Company get() {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("get company for employee {}", employeeId);
        return service.get();
    }
}
