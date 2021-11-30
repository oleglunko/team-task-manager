package ru.oleglunko.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.model.Employee;
import ru.oleglunko.taskmanager.repository.EmployeeRepository;

import java.util.List;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final CompanyService companyService;
    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository repository, CompanyService companyService, DepartmentService departmentService) {
        this.repository = repository;
        this.companyService = companyService;
        this.departmentService = departmentService;
    }

    @Transactional
    public Employee create(Employee employee, int departmentId) {
        Assert.notNull(employee, "Employee must not be null");
        employee.setCompany(companyService.get());
        employee.setDepartment(departmentService.get(departmentId));
        return repository.save(employee);
    }

    @Transactional
    public void update(Employee employee, int departmentId) {
        Assert.notNull(employee, "Employee must not be null");
        employee.setCompany(companyService.get());
        employee.setDepartment(departmentService.get(departmentId));
        checkNotFoundWithId(repository.save(employee), employee.getId());
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public Employee get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public List<Employee> getAllByDepartmentId(int departmentId) {
        return repository.findAllByDepartmentIdOrderByLastName(departmentId);
    }
}
