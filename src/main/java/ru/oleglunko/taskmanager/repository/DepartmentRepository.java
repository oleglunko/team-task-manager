package ru.oleglunko.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.oleglunko.taskmanager.model.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Override
    @Query("SELECT d FROM Department d ORDER BY d.name")
    List<Department> findAll();

    @Modifying
    @Query("DELETE FROM Department d WHERE d.id = ?1")
    int delete(int id);
}
