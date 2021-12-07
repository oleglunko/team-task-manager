package ru.oleglunko.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.model.Department;
import ru.oleglunko.taskmanager.repository.DepartmentRepository;

import java.util.List;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

//TODO refactoring
@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final CompanyService companyService;

    public DepartmentService(DepartmentRepository repository, CompanyService companyService) {
        this.repository = repository;
        this.companyService = companyService;
    }

    public Department get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Department> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Department create(Department department) {
        Assert.notNull(department, "Department must not be null");
        department.setCompany(companyService.get());
        return repository.save(department);
    }

    @Transactional
    public void update(Department department) {
        Assert.notNull(department, "Department must not be null");
        get(department.getId());
        department.setCompany(companyService.get());
        repository.save(department);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
