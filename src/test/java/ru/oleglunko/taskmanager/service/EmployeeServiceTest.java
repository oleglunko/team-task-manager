package ru.oleglunko.taskmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.oleglunko.taskmanager.model.Employee;
import ru.oleglunko.taskmanager.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.oleglunko.taskmanager.EmployeeTestData.DEPARTMENT_ID;
import static ru.oleglunko.taskmanager.EmployeeTestData.EMPLOYEE_ID;
import static ru.oleglunko.taskmanager.EmployeeTestData.EMPLOYEE_MATCHER;
import static ru.oleglunko.taskmanager.EmployeeTestData.NOT_FOUND_ID;
import static ru.oleglunko.taskmanager.EmployeeTestData.departmentEmployees;
import static ru.oleglunko.taskmanager.EmployeeTestData.employee1;
import static ru.oleglunko.taskmanager.EmployeeTestData.allEmployees;
import static ru.oleglunko.taskmanager.EmployeeTestData.getNew;
import static ru.oleglunko.taskmanager.EmployeeTestData.getUpdated;

public class EmployeeServiceTest extends AbstractServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void get() {
        Employee actual = service.get(EMPLOYEE_ID);
        EMPLOYEE_MATCHER.assertMatch(actual, employee1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID));
    }

    @Test
    void getAll() {
        EMPLOYEE_MATCHER.assertMatch(service.getAll(), allEmployees);
    }

    @Test
    void getAllByDepartmentId() {
        EMPLOYEE_MATCHER.assertMatch(service.getAllByDepartmentId(DEPARTMENT_ID), departmentEmployees);
    }

    @Test
    void delete() {
        service.delete(EMPLOYEE_ID);
        assertThrows(NotFoundException.class, () -> service.get(EMPLOYEE_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID));
    }

    @Test
    void create() {
        Employee created = service.create(getNew(), DEPARTMENT_ID);
        int newId = created.getId();
        Employee newEmployee = getNew();
        newEmployee.setId(newId);
        EMPLOYEE_MATCHER.assertMatch(created, newEmployee);
        EMPLOYEE_MATCHER.assertMatch(service.get(newId), newEmployee);
    }

    @Test
    void update() {
        Employee updated = getUpdated();
        service.update(updated, DEPARTMENT_ID);
        EMPLOYEE_MATCHER.assertMatch(service.get(EMPLOYEE_ID), getUpdated());
    }
}
