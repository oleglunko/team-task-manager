package ru.oleglunko.taskmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.oleglunko.taskmanager.model.Task;
import ru.oleglunko.taskmanager.util.exception.NotFoundException;
import ru.oleglunko.taskmanager.util.exception.UnsupportedOperationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.oleglunko.taskmanager.TaskTestData.EMPLOYEE_ID1;
import static ru.oleglunko.taskmanager.TaskTestData.EMPLOYEE_ID2;
import static ru.oleglunko.taskmanager.TaskTestData.MANAGER_ID1;
import static ru.oleglunko.taskmanager.TaskTestData.MANAGER_ID2;
import static ru.oleglunko.taskmanager.TaskTestData.NOT_FOUND_ID;
import static ru.oleglunko.taskmanager.TaskTestData.PERFORMED_TASK_ID;
import static ru.oleglunko.taskmanager.TaskTestData.TASK_ID;
import static ru.oleglunko.taskmanager.TaskTestData.TASK_MATCHER;
import static ru.oleglunko.taskmanager.TaskTestData.departmentTasks;
import static ru.oleglunko.taskmanager.TaskTestData.getAccepted;
import static ru.oleglunko.taskmanager.TaskTestData.getDeclined;
import static ru.oleglunko.taskmanager.TaskTestData.getNew;
import static ru.oleglunko.taskmanager.TaskTestData.getPerformed;
import static ru.oleglunko.taskmanager.TaskTestData.getUpdated;
import static ru.oleglunko.taskmanager.TaskTestData.task1;

public class TaskServiceTest extends AbstractServiceTest {

    @Autowired
    private TaskService service;

    @Test
    void getInEmployeesDepartment() {
        TASK_MATCHER.assertMatch(service.getInEmployeesDepartment(TASK_ID, EMPLOYEE_ID1), task1);
    }

    @Test
    void getInEmployeesDepartmentNotFound() {
        assertThrows(NotFoundException.class, () -> service.getInEmployeesDepartment(NOT_FOUND_ID, EMPLOYEE_ID1));
    }

    @Test
    void getNotInEmployeesDepartment() {
        assertThrows(NotFoundException.class, () -> service.getInEmployeesDepartment(TASK_ID, EMPLOYEE_ID2));
    }

    @Test
    void getAllInEmployeesDepartment() {
        TASK_MATCHER.assertMatch(service.getAllInEmployeesDepartment(EMPLOYEE_ID1), departmentTasks);
    }

    @Test
    void delete() {
        service.delete(TASK_ID, MANAGER_ID1);
        assertThrows(NotFoundException.class, () -> service.getById(TASK_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID, MANAGER_ID1));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(TASK_ID, MANAGER_ID2));
    }

    @Test
    void create() {
        Task created = service.create(getNew(), MANAGER_ID1, EMPLOYEE_ID1);
        int newId = created.getId();
        Task newTask = getNew();
        newTask.setId(newId);
        TASK_MATCHER.assertMatch(created, newTask);
        TASK_MATCHER.assertMatch(service.getById(newId), newTask);
    }

    @Test
    void update() {
        Task updated = getUpdated();
        service.update(updated, MANAGER_ID1, EMPLOYEE_ID1);
        TASK_MATCHER.assertMatch(service.getById(TASK_ID), getUpdated());
    }

    @Test
    void updateNotOwn() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), MANAGER_ID2, EMPLOYEE_ID2));
    }

    @Test
    void perform() {
        service.perform(TASK_ID, EMPLOYEE_ID1);
        TASK_MATCHER.assertMatch(service.getById(TASK_ID), getPerformed());
    }

    @Test
    void performWithNotPerformer() {
        assertThrows(UnsupportedOperationException.class, () -> service.perform(TASK_ID, EMPLOYEE_ID2));
    }

    //TODO change tests
//    @Test
//    void accept() {
//        service.accept(PERFORMED_TASK_ID, MANAGER_ID1);
//        TASK_MATCHER.assertMatch(service.getById(PERFORMED_TASK_ID), getAccepted());
//    }
//
//    @Test
//    void acceptNotPerformed() {
//        assertThrows(UnsupportedOperationException.class, () -> service.accept(TASK_ID, MANAGER_ID1));
//    }
//
//    @Test
//    void acceptNotOwn() {
//        assertThrows(UnsupportedOperationException.class, () -> service.accept(TASK_ID, MANAGER_ID2));
//    }
//
//    @Test
//    void decline() {
//        service.decline(PERFORMED_TASK_ID, MANAGER_ID1);
//        TASK_MATCHER.assertMatch(service.getById(PERFORMED_TASK_ID), getDeclined());
//    }
//
//    @Test
//    void declineNotOwn() {
//        assertThrows(UnsupportedOperationException.class, () -> service.decline(PERFORMED_TASK_ID, MANAGER_ID2));
//    }
}
