package ru.oleglunko.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.model.Task;
import ru.oleglunko.taskmanager.repository.TaskRepository;
import ru.oleglunko.taskmanager.util.exception.UnsupportedOperationException;

import java.util.List;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final EmployeeService employeeService;

    public TaskService(TaskRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Transactional
    public Task getForEmployee(int id, int employeeId) {
        Task task = checkNotFoundWithId(repository.findById(id).orElse(null), id);
        Integer taskDepartmentId = task.getAuthor().getDepartment().getId();
        Integer employeesDepartmentId = employeeService.get(employeeId).getDepartment().getId();
        if (!taskDepartmentId.equals(employeesDepartmentId)) {
            checkNotFoundWithId(null, id);
        }
        return task;
    }

    @Transactional
    public List<Task> getAllForEmployee(int employeeId) {
        Integer departmentId = employeeService.get(employeeId).getDepartment().getId();
        return repository.findAllInDepartment(departmentId);
    }

    @Transactional
    public void delete(int id, int employeeId) {
        checkNotFoundWithId(repository.delete(id, employeeId) != 0, id);
    }

    @Transactional
    public Task create(Task task, int authorId, int performerId) {
        Assert.notNull(task, "Task must not be null");
        task.setAuthor(employeeService.get(authorId));
        task.setPerformer(employeeService.get(performerId));
        return repository.save(task);
    }

    @Transactional
    public void update(Task task, int authorId, int performerId) {
        Assert.notNull(task, "Task must not be null");
        Task maybeTask = repository.findById(task.getId())
                .filter(entity -> entity.getAuthor().getId() == authorId)
                .orElse(null);
        if (maybeTask == null) {
            checkNotFoundWithId(null, task.getId());
        } else {
            task.setAuthor(employeeService.get(authorId));
            task.setPerformer(employeeService.get(performerId));
            repository.save(task);
        }
    }

    @Transactional
    public void perform(int id, int employeeId) {
        Task performedTask = getById(id);
        if (performedTask.getPerformer().getId() == employeeId) {
            performedTask.setPerformed(true);
            repository.save(performedTask);
        } else {
            throw new UnsupportedOperationException("You cannot perform this operation");
        }
    }

    @Transactional
    public void accept(int id, int employeeId) {
        Task performedTask = getById(id);
        if (performedTask.getAuthor().getId() == employeeId && performedTask.isPerformed()) {
            performedTask.setAccepted(true);
            repository.save(performedTask);
        } else {
            throw new UnsupportedOperationException("You cannot perform this operation");
        }
    }

    @Transactional
    public void decline(int id, int employeeId) {
        Task performedTask = getById(id);
        if (performedTask.getAuthor().getId() == employeeId && performedTask.isPerformed()) {
            performedTask.setPerformed(false);
            repository.save(performedTask);
        } else {
            throw new UnsupportedOperationException("You cannot perform this operation");
        }
    }

    public Task getById(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }
}
