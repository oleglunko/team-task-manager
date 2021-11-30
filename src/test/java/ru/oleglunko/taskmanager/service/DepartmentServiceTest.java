package ru.oleglunko.taskmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.oleglunko.taskmanager.model.Department;
import ru.oleglunko.taskmanager.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.oleglunko.taskmanager.DepartmentTestData.DEPARTMENT_ID;
import static ru.oleglunko.taskmanager.DepartmentTestData.DEPARTMENT_MATCHER;
import static ru.oleglunko.taskmanager.DepartmentTestData.NOT_FOUND_ID;
import static ru.oleglunko.taskmanager.DepartmentTestData.department1;
import static ru.oleglunko.taskmanager.DepartmentTestData.departments;
import static ru.oleglunko.taskmanager.DepartmentTestData.getNew;
import static ru.oleglunko.taskmanager.DepartmentTestData.getUpdated;

public class DepartmentServiceTest extends AbstractServiceTest {

    @Autowired
    private DepartmentService service;

    @Test
    void get() {
        DEPARTMENT_MATCHER.assertMatch(service.get(DEPARTMENT_ID), department1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID));
    }

    @Test
    void getAll() {
        DEPARTMENT_MATCHER.assertMatch(service.getAll(), departments);
    }

    @Test
    void create() {
        Department created = service.create(getNew());
        int newId = created.getId();
        Department newDepartment = getNew();
        newDepartment.setId(newId);
        DEPARTMENT_MATCHER.assertMatch(created, newDepartment);
        DEPARTMENT_MATCHER.assertMatch(service.get(newId), newDepartment);
    }

    @Test
    void update() {
        Department updated = getUpdated();
        service.update(updated);
        DEPARTMENT_MATCHER.assertMatch(service.get(DEPARTMENT_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(DEPARTMENT_ID);
        assertThrows(NotFoundException.class, () -> service.get(DEPARTMENT_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID));
    }
}
