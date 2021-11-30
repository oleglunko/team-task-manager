package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
