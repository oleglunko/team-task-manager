package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
