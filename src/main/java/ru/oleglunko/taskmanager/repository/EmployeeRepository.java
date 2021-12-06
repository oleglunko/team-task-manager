package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByDepartmentIdOrderByLastName(int departmentId);

    @Override
    @Query("SELECT e FROM Employee e ORDER BY e.lastName")
    List<Employee> findAll();

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = ?1")
    int delete(int id);

    Employee getByLogin(String login);
}
