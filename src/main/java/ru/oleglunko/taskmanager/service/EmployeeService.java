package ru.oleglunko.taskmanager.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.AuthorizedEmployee;
import ru.oleglunko.taskmanager.model.Employee;
import ru.oleglunko.taskmanager.repository.EmployeeRepository;

import java.util.List;

import static ru.oleglunko.taskmanager.util.EmployeeUtil.prepareToSave;
import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

//TODO refactoring
@Service("employeeService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository repository;
    private final CompanyService companyService;
    private final DepartmentService departmentService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository, CompanyService companyService, DepartmentService departmentService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.companyService = companyService;
        this.departmentService = departmentService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Employee create(Employee employee, int departmentId) {
        Assert.notNull(employee, "Employee must not be null");
        return prepareAndSave(employee, departmentId);
    }

    @Transactional
    public void update(Employee employee, int departmentId) {
        Assert.notNull(employee, "Employee must not be null");
        get(employee.getId());
        prepareAndSave(employee, departmentId);
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

    @Transactional
    public void enable(int id, boolean enabled) {
        Employee employee = get(id);
        employee.setEnabled(enabled);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = repository.getByLogin(login.toLowerCase());
        if (employee == null) {
            throw new UsernameNotFoundException("Employee " + login + " is not found");
        }
        return new AuthorizedEmployee(employee);
    }

    private Employee prepareAndSave(Employee employee, int departmentId) {
        employee.setCompany(companyService.get());
        employee.setDepartment(departmentService.get(departmentId));
        return repository.save(prepareToSave(employee, passwordEncoder));
    }
}
