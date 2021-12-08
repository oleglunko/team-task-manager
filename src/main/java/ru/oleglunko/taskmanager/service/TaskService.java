package ru.oleglunko.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.model.Task;
import ru.oleglunko.taskmanager.repository.TaskRepository;
import ru.oleglunko.taskmanager.util.exception.UnsupportedOperationException;

import java.util.List;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

//TODO refactoring
@Service
public class TaskService {

    private final TaskRepository repository;
    private final EmployeeService employeeService;

    public TaskService(TaskRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @Transactional
    public Task getInEmployeesDepartment(int id, int employeeId) {
        Task task = getById(id);
        Integer taskDepartmentId = task.getAuthor().getDepartment().getId();
        Integer employeesDepartmentId = employeeService.get(employeeId).getDepartment().getId();
        if (!taskDepartmentId.equals(employeesDepartmentId)) {
            checkNotFoundWithId(null, id);
        }
        return task;
    }

    public Task getById(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Transactional
    public List<Task> getAllInEmployeesDepartment(int employeeId) {
        Integer departmentId = employeeService.get(employeeId).getDepartment().getId();
        return repository.findAllInDepartment(departmentId);
    }

    @Transactional
    public List<Task> getAllForPerformer(int employeeId) {
        return repository.findAllForPerformer(employeeId);
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
        } else {
            throw new UnsupportedOperationException("You cannot perform this operation");
        }
    }

    @Transactional
    public void acceptOrDecline(int id, int employeeId, boolean accepted) {
        Task performedTask = getById(id);
        if (performedTask.getAuthor().getId() != employeeId || !performedTask.isPerformed()) {
            throw new UnsupportedOperationException("You cannot perform this operation");
        } else if (accepted){
            performedTask.setAccepted(true);
        } else {
            performedTask.setAccepted(false);
            performedTask.setPerformed(false);
        }
    }
}
