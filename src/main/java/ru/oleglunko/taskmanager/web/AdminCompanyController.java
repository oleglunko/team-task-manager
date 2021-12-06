package ru.oleglunko.taskmanager.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.oleglunko.taskmanager.model.Company;
import ru.oleglunko.taskmanager.service.CompanyService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = AdminCompanyController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminCompanyController {

    static final String REST_URL = "/rest/admin/company";

    private final CompanyService service;

    public AdminCompanyController(CompanyService service) {
        this.service = service;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Company company) {
        int employeeId = SecurityUtil.authEmployeeId();
        log.info("update {} by admin {}", company, employeeId);
        service.update(company);
    }
}
