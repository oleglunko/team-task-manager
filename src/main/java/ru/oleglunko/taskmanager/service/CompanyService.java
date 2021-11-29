package ru.oleglunko.taskmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.oleglunko.taskmanager.model.Company;
import ru.oleglunko.taskmanager.repository.CompanyRepository;
import ru.oleglunko.taskmanager.util.exception.IllegalArgumentException;

import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithEmployeeId;
import static ru.oleglunko.taskmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CompanyService {

    private CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);

    }

    public Company getByEmployeeId(int employeeId) {
        return checkNotFoundWithEmployeeId(repository.findByEmployeesId(employeeId), employeeId);
    }

    @Transactional
    public void update(Company company) {
        Assert.notNull(company, "Company must not be null");
        if (!company.isNew() && repository.existsById(company.getId())) {
            repository.save(company);
        } else {
            throw new IllegalArgumentException("Only one company can exists in the application");
        }
    }
}
