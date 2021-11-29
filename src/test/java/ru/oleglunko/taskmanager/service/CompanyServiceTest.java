package ru.oleglunko.taskmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.oleglunko.taskmanager.model.Company;
import ru.oleglunko.taskmanager.util.exception.IllegalArgumentException;
import ru.oleglunko.taskmanager.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.oleglunko.taskmanager.CompanyTestData.COMPANY_ID;
import static ru.oleglunko.taskmanager.CompanyTestData.COMPANY_MATCHER;
import static ru.oleglunko.taskmanager.CompanyTestData.EMPLOYEE_ID;
import static ru.oleglunko.taskmanager.CompanyTestData.NOT_FOUND_ID;
import static ru.oleglunko.taskmanager.CompanyTestData.company;
import static ru.oleglunko.taskmanager.CompanyTestData.getNew;
import static ru.oleglunko.taskmanager.CompanyTestData.getUpdated;

public class CompanyServiceTest extends AbstractServiceTest {

    @Autowired
    private CompanyService service;

    @Test
    void getByEmployeeId() {
        Company actual = service.getByEmployeeId(EMPLOYEE_ID);
        COMPANY_MATCHER.assertMatch(actual, company);
    }

    @Test
    void getByEmployeeIdNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByEmployeeId(NOT_FOUND_ID));
    }

    @Test
    void update() {
        Company updated = getUpdated();
        service.update(updated);
        COMPANY_MATCHER.assertMatch(service.get(COMPANY_ID), getUpdated());
    }

    @Test
    void updateWithNewCompany() {
        assertThrows(IllegalArgumentException.class, () -> service.update(getNew()));
    }

}
